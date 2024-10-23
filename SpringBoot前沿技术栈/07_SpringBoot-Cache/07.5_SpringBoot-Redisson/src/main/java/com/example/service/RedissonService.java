package com.example.service;

import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-07-09 23:42
 * @apiNote TODO
 */
@Service
public class RedissonService {

    @Resource
    private RedissonClient redissonClient;

    public void lock(String lockName, long expireTime) {
        RLock rLock = redissonClient.getLock(lockName);
        try {
            boolean isLocked = rLock.tryLock(expireTime, TimeUnit.MILLISECONDS);
            if (isLocked) {
                // TODO
            }
        } catch (Exception e) {
            rLock.unlock();
        }
    }
}
