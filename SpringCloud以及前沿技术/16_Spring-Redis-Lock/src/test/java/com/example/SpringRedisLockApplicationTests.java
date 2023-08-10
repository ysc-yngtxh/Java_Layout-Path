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
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class SpringRedisLockApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private DefaultRedisScript<Boolean> redisScript;

    /**项目中使用Redis批量操作数据时遇到问题，因为数据量的不确定，且必须保证批量操作数据的事务性
       所以必须使用Redis中的分布式锁进行处理*/
    @Test
    public void test1() {
        // redis中如果有K1值那么结果就为false，表示已经有锁。没有K1值结果为true，并创建K1缓存值,并获取锁
        Boolean success = redisTemplate.opsForValue().setIfAbsent("K1", "V1");
        if (success) {
            //缓存中key为order的值进行自增
            redisTemplate.opsForValue().increment("order");
            //释放锁
            redisTemplate.delete("K1");
        } else {
            log.error("有线程在使用，请稍后再试！");
        }
    }

    /**上面的test1测试有缺陷：就是当前线程在自增缓存时候如果出现异常。这时候其他线程过来发现并没有释放锁，就只会走else。这不是我们想要的*/
    @Test
    public void test2() {
        // redis中如果有K1值那么结果就为false，表示上锁。没有K1值结果为true，并创建K1缓存值,并获取锁  设置5秒锁的失效时间
        Boolean success = redisTemplate.opsForValue().setIfAbsent("K1", "V1", 5 , TimeUnit.SECONDS);
        if (success) {
            // 缓存中key为order的值进行自增
            redisTemplate.opsForValue().increment("order");
            // Integer.parseInt("ysc");   这里模拟一下异常
            // 释放锁
            redisTemplate.delete("K1");
        } else {
            log.error("有线程在使用，请稍后再试！");
        }
    }


    /**上面的test2测试还是有缺陷：虽然我们增加了锁的失效时间，避免了死锁的产生，但是会出现事务上的安全问题。
     *  比如：线程1出现异常，在5秒后线程2获取到了锁，线程2正常运行并执行了释放锁的步骤，总共耗时3秒。
     *       这个时候线程3去执行并且上锁K1，你会发现线程3还没执行到释放锁的步骤，锁就已经被释放了。
     *       因为在线程2获取锁的时候，线程2没用到5秒便执行完，线程2的5秒后释放的是线程3的K1锁。
     */
    @Test
    public void test3() {
        initRedisScript();
        String value = UUID.randomUUID().toString();

        // redis中如果有K1值那么结果就为false，表示上锁。没有K1值结果为true，并创建K1缓存值,并获取锁  设置120秒锁的失效时间
        Boolean success = redisTemplate.opsForValue().setIfAbsent("K1", value, 120 , TimeUnit.SECONDS);
        if (success) {
            // 缓存中key为order的值进行自增
            redisTemplate.opsForValue().increment("order");
            /**这里是通过给K1赋一个随机值value。先去获取到锁，然后再去判断锁的值是否一致，一致的话才会删除
              但是这里你又会想说，那这样要什么脚本嘛，可以自己写啊。这里我还不大懂，反正老师说，获取锁，判断值，删除三个操作不是原子性的。*/
            redisTemplate.execute(redisScript, Collections.singletonList("K1"), value);
        } else {
            log.error("有线程在使用，请稍后再试！");
        }
    }

    /*脚本位置*/
    public void initRedisScript() {
        redisScript = new DefaultRedisScript<Boolean>();
        redisScript.setResultType(Boolean.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis.lua")));
    }

}
