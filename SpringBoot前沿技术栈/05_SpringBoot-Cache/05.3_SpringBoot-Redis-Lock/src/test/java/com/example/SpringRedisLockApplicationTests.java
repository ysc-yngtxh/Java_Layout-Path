package com.example;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class SpringRedisLockApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private DefaultRedisScript<Boolean> redisScript1;
    private DefaultRedisScript<Long> redisScript2;

    /**
     * 项目中使用Redis批量操作数据时，因为数据量的不确定，且必须保证批量操作数据的事务性，
     * 所以必须使用Redis中的分布式锁进行处理，保证数据安全。
     */
    @Test
    public void test1() {
        /**
         * setIfAbsent(K key, V value)方法表示的是：
         * 如果键原本不存在则设置键值对，返回 true；如果键已经存在，则不做任何操作并返回 false。
         */
        Boolean success = redisTemplate.opsForValue().setIfAbsent("K1", "V1");
        if (Boolean.TRUE.equals(success)) {
            log.info("上锁进来了吗？");
            // 先判断库存是否充足
            if (redisTemplate.hasKey("stock")) {
                int stock = Integer.parseInt( redisTemplate.opsForValue().get("stock") );
                if (stock > 0) {
                    // 缓存中key为stock的库存进行自减
                    redisTemplate.opsForValue().decrement("stock");
                }
            }
            // 释放锁
            redisTemplate.delete("K1");
        } else {
            log.error("有线程在使用，请稍后再试！");
            try {
                Thread.sleep(1000); // 等待1秒
                test1();            // 等待后再次执行方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上面的test1()测试有一个很明显的缺陷：
     * 就是当前线程A在自减库存时候如果出现异常，这时候其他线程过来发现并没有释放锁(因为异常一直被阻塞着,没办法往下去执行我们的删除锁逻辑)，
     * 而且这个阻塞时间我们没法控制，可能线程A锁一直锁到我孩子都出生去打酱油了，其它线程只能干愣眼走else。
     */
    @Test
    public void test2() {
        /**
         * setIfAbsent(K key, V value, long timeout, TimeUnit unit)方法表示的是
         * 如果Redis中不存在key=K1值，就添加一个key=K1的数据，并设置其value=V1，返回一个true，而且设置一个 5秒的过期时间
         * 如果存在，那么就不进行设置value或者value值覆盖，并返回一个 false
         */
        // redis中如果有K1值那么结果就为false，表示上锁。没有K1值结果为true，并创建K1缓存值,并获取锁  设置5秒锁的失效时间
        Boolean success = redisTemplate.opsForValue().setIfAbsent("K1", "V1", 5 , TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(success)) {
            log.info("上锁进来了吗？");
            if (redisTemplate.hasKey("stock")) {
                // 先判断库存是否充足
                int stock = Integer.parseInt( redisTemplate.opsForValue().get("stock") );
                if (stock > 0) {
                    // 缓存中key为stock的库存进行自减
                    redisTemplate.opsForValue().decrement("stock");
                }
            }
            // 释放锁
            redisTemplate.delete("K1");
        } else {
            log.error("有线程在使用，请稍后再试！");
            try {
                Thread.sleep(1000); // 等待1秒
                test2();            // 等待后再次执行方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上面的test2测试还是有缺陷：虽然我们增加了锁的失效时间，避免了死锁的产生，但是会出现事务上的安全问题。
     *  比如：A线程在执行自减的时候，刚巧卡了(别问为什么 就是这么巧)，一直卡到锁自动过期时间到了。
     *       然后这时候 B线程获取到锁准备走 if判断，而我的 A线程开始执行删除释放了锁，且这个是他娘的 B线程的锁｡--（哭死）
     *       由于释放了锁，因此C线程获取到了锁。然后 B线程执行 delete()方法删除了 C线程的锁。D线程获取到了锁......(循环♻️)
     */
    @Test
    public void test3() {
        // 这里的 value 值要设置为随机值，不要使用固定值。
        String value = UUID.randomUUID().toString();

        // redis中如果有K1值那么结果就为false，表示上锁。没有K1值结果为true，并创建K1缓存值,并获取锁  设置5秒锁的失效时间
        Boolean success = redisTemplate.opsForValue().setIfAbsent("K1", value, 5, TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(success)) {
            log.info("上锁进来了吗？");
            if (redisTemplate.hasKey("stock")) {
                // 先判断库存是否充足
                int stock = Integer.parseInt( redisTemplate.opsForValue().get("stock") );
                if (stock > 0) {
                    // 缓存中key为stock的库存进行自减
                    redisTemplate.opsForValue().decrement("stock");
                }
            }
            // 判断当前线程通过 UUID的一个随机值是否还与我的锁值是相等的
            // 如果不相等，说明我的锁值过期了，释放了锁，后续线程获取到了新锁，且这个新锁的值随机，这才造成的不相等。
            // 因而不会进入 if判断中，避免去删除掉新锁，造成后续线程无锁执行
            if (Objects.equals(redisTemplate.opsForValue().get("K1"), value)) {
                // 能执行到 if判断里，说明我的锁还未过期。所以删除掉当前线程锁，让后续线程得以执行
                redisTemplate.delete("K1");
            }
        } else {
            log.error("有线程在使用，请稍后再试！");
            try {
                Thread.sleep(1000); // 等待1秒
                test3();            // 等待后再次执行方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上面的test3测试还是有缺陷：
     * 虽然我们增加了锁值判断，但是 if(Objects.equals(redisTemplate.opsForValue().get("K1"), value)) 获取锁,判断值,删除锁这三个操作不是原子性操作
     * 比如： A 线程去执行获取锁 redisTemplate.opsForValue().get("K1") 这一步，得到了锁值后并进行判断为true，
     *       还没执行if中的删除锁操作时，锁的过期时间到了。
     *       于是 B 线程就通过setIfAbsent()方法重新设置锁值，设置好了之后 A 线程再执行删除锁操作，
     *       然后删除掉的实际是 B线程的锁. 接着就又会出现 test2测试中出现当前线程删除下一线程锁的循环情况
     */
    @Test
    public void test4() {
        initRedisDecrScript();
        // 这里的 value 值要设置为随机值，不要使用固定值。
        String value = UUID.randomUUID().toString();

        // redis中如果有K1值那么结果就为false，表示上锁。没有K1值结果为true，并创建K1缓存值,并获取锁  设置5秒锁的失效时间
        Boolean success = redisTemplate.opsForValue().setIfAbsent("K1", value, 5, TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(success)) {
            log.info("上锁进来了吗？");
            if (redisTemplate.hasKey("stock")) {
                // 先判断库存是否充足
                int stock = Integer.parseInt( redisTemplate.opsForValue().get("stock") );
                if (stock > 0) {
                    // 缓存中key为stock的库存进行自减
                    redisTemplate.opsForValue().decrement("stock");
                }
            }
            // 这里使用的 Lua脚本，lua是不支持多线程的，保证获取锁、判断值、删除锁这三个操作是原子性操作。
            redisTemplate.execute(redisScript1, Collections.singletonList("K1"), value);
        } else {
            log.error("有线程在使用，请稍后再试！");
            try {
                Thread.sleep(1000); // 等待1秒
                test4();            // 等待后再次执行方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /*脚本配置*/
    public void initRedisDecrScript() {
        redisScript1 = new DefaultRedisScript<Boolean>();
        redisScript1.setResultType(Boolean.class);
        redisScript1.setScriptSource(new ResourceScriptSource(new ClassPathResource("Decr.lua")));
    }

    /**
     * 上面的test4测试还是有缺陷：
     * 虽然我们增加了锁值判断的原子性，避免了在多线程情况里后续线程的一个循环无锁执行，但对于当前线程来说，操作是有风险的
     * 比如：库存只剩一件了，按理来说只能被一个线程执行自减。现在A线程在执行自减的时候，又刚巧卡了(别问为什么 就是这么巧)，
     *      一直卡到锁自动过期时间到了。B线程获取到了锁并进入了库存判断，由于我们 A线程执行自减的时候卡住了，还没自减完成，
     *      所以 B线程通过了 库存>0 的判断，也要执行自减。那么最终的结果就会是 库存=-1。
     * <p>
     * 对于后续线程的执行确实能约束不造成影响，但无法保证的是一旦某个线程上锁时间过期，就很有可能使我们自减的数据偏离预期
     */
    @Test
    public void test5() {
        initRedisStockScript();
        // 这里通过execute()方法执行 Lua脚本，可以保证我们脚本里所有步骤的一个原子性(脚本里的步骤是一个整体，不可再分)，
        // 这样的话，我们 ①判断库存>0  ②操作库存自减  ③删除释放锁 这些步骤保证是一个原子操作，且lua是不支持多线程的
        redisTemplate.execute(redisScript2, Collections.singletonList("stock"));
    }
    /*脚本配置*/
    public void initRedisStockScript() {
        redisScript2 = new DefaultRedisScript<Long>();
        redisScript2.setResultType(Long.class);
        redisScript2.setScriptSource(new ResourceScriptSource(new ClassPathResource("Stock.lua")));
    }
}
