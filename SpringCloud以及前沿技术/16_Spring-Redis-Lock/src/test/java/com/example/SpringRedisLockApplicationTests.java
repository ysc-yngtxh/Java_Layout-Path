package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class SpringRedisLockApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private DefaultRedisScript<Boolean> redisScript;

    /**项目中使用Redis批量操作数据时遇到问题，因为数据量的不确定，且必须保证批量操作数据的事务性
       所以必须使用Redis中的分布式锁进行处理*/
    @Test
    public void test1() {
        /**
         * setIfAbsent(K key, V value)方法表示的是
         * 如果Redis中不存在key=K1值，就添加一个key=K1的数据，并设置其value=V1，返回一个true
         * 如果存在，那么就不进行设置value或者value值覆盖，并返回一个 false
         */
        Boolean success = redisTemplate.opsForValue().setIfAbsent("K1", "V1");
        if (Boolean.TRUE.equals(success)) {
            // 缓存中key为order的值进行自增
            redisTemplate.opsForValue().increment("order");
            // 释放锁
            redisTemplate.delete("K1");
        } else {
            log.error("有线程在使用，请稍后再试！");
        }
    }

    /**上面的test1测试有缺陷：
     * 就是当前线程在自增缓存时候如果出现异常。这时候其他线程过来发现并没有释放锁(出现异常一直阻塞着)，就只会走else。
     */
    @Test
    public void test2() {
        /**
         * setIfAbsent(K key, V value, long timeout, TimeUnit unit)方法表示的是
         * 如果Redis中不存在key=K1值，就添加一个key=K1的数据，并设置其value=V1，返回一个true，而且设置一个 5 秒的过期时间
         * 如果存在，那么就不进行设置value或者value值覆盖，并返回一个 false
         */
        // redis中如果有K1值那么结果就为false，表示上锁。没有K1值结果为true，并创建K1缓存值,并获取锁  设置5秒锁的失效时间
        Boolean success = redisTemplate.opsForValue().setIfAbsent("K1", "V1", 5 , TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(success)) {
            // 缓存中key为order的值进行自增
            redisTemplate.opsForValue().increment("order");
            // Integer.parseInt("ysc");   // 这里模拟一下异常
            // 释放锁
            redisTemplate.delete("K1");
        } else {
            log.error("有线程在使用，请稍后再试！");
        }
    }


    /**
     * 上面的test2测试还是有缺陷：虽然我们增加了锁的失效时间，避免了死锁的产生，但是会出现事务上的安全问题。
     *  比如：A线程在执行自增的时候，刚巧卡了(别问为什么这么巧)，一直卡到锁自动过期时间到了。
     *       然后这时候 B线程获取到锁准备走 if判断，而我的 A线程好死不死的开始动了，并且释放了锁，且这个是他娘的 B 线程的锁｡--（哭死）
     *       由于释放了锁，因此C线程获取到了锁。然后 B 线程执行 delete()方法删除了锁。D 线程获取到了锁......
     */
    @Test
    public void test3() {
        initRedisScript();
        // 这里的 value 值要设置为随机值，不要使用固定值。
        // 否则会出现线程不安全: 如果value值是固定的，那Lua脚本就只会走then语句.
        // 因为后续线程无论是通过上一个线程正常释放锁去设置setIfAbsent()还是通过上一线程的锁自动过期去设置setIfAbsent()值，
        // 这个值由于是固定的，所以在Lua脚本中的判断是一直相等的，所以value不能使用固定值
        String value = UUID.randomUUID().toString();

        // redis中如果有K1值那么结果就为false，表示上锁。没有K1值结果为true，并创建K1缓存值,并获取锁  设置5秒锁的失效时间
        Boolean success = redisTemplate.opsForValue().setIfAbsent("K1", value, 5 , TimeUnit.SECONDS);
        if (Boolean.TRUE.equals(success)) {
            // 先判断库存是否充足
            Object stock = redisTemplate.opsForValue().get("stock");
            if (Objects.nonNull(stock) && (int)stock > 0) {
                // 缓存中key为stock的库存进行自减
                redisTemplate.opsForValue().increment("stock");
            }
            /**
             * 这里使用的 Lua脚本 这里是通过给K1赋一个随机值value。先去获取到锁，然后再去判断锁的值是否一致，一致的话才会删除
             * 但是这里你又会想说，那这样要什么脚本嘛，可以自己写啊。这里我还不大懂，反正老师说，获取锁，判断值，删除三个操作不是原子性的。
             */
            redisTemplate.execute(redisScript, Collections.singletonList("K1"), value);
        } else {
            log.error("有线程在使用，请稍后再试！");
            // 不能获取到锁
            try {
                Thread.sleep(1000); // 等待1秒
                test3();  // 等待后再次执行方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*脚本位置*/
    public void initRedisScript() {
        redisScript = new DefaultRedisScript<Boolean>();
        redisScript.setResultType(Boolean.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("Decr.lua")));
    }

}
