package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 游家纨绔
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    // 这一个类是redis依赖提供的，就在Maven的spring-data-redis

    @Override
    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String count) {
        String query = (String) redisTemplate.opsForValue().get(count);

        Map<String, String> hashMap = new HashMap<>(2);
        hashMap.put("redis", "redis");
        hashMap.put("mysql", "mysql");
        for (Map.Entry<String, String> keyValue : hashMap.entrySet()) {
            redisTemplate.opsForHash().put("key", keyValue.getKey(), keyValue.getValue());
        }
        return query;
    }
}
