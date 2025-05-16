package com.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringRedisApplicationTests1 {

    private final Logger log = LoggerFactory.getLogger(SpringRedisApplicationTests1.class);

    // Redis操作对象，相当于使用默认泛型 RedisTemplate<Object, Object> 注入
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("redisDemo1", "ysc-游家纨绔");
        log.info("内存数据：{}", redisTemplate.opsForValue().get("redisDemo1"));
    }
}
