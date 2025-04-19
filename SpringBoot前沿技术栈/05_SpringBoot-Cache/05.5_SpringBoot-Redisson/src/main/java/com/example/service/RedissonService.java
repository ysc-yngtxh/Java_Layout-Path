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

    // 分布式锁
    public void lock(String lockName, long expireTime) {
        // 参数(lockName)为锁的名称，作为Redis中的key
        RLock rLock = redissonClient.getLock(lockName);
        try {
            // tryLock() 方法尝试获取锁。如果在指定时间内获取到锁返回true，否则返回false
            // 参数一：等待获取锁的最大时间（毫秒）
            // 参数二：锁的自动释放时间（leaseTime，毫秒）
            // 参数三：时间单位
            boolean isLocked = rLock.tryLock(expireTime, 30, TimeUnit.MILLISECONDS);

            // 当没有显式设置 leaseTime参数(即第二个参数)，看门狗即可生效。
            boolean isLocked2 = rLock.tryLock();
            boolean isLocked3 = rLock.tryLock(expireTime, TimeUnit.MILLISECONDS);

            if (isLocked) {
                // TODO
            }
        } catch (Exception e) {
            //
            if (rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }
}
