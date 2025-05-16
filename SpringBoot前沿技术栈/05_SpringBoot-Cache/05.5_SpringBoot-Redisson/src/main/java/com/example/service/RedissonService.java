package com.example.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-07-09 23:00
 * @apiNote TODO
 */
@Service
public class RedissonService {

	@Autowired
	private RedissonClient redissonClient;

	// 分布式锁
	public void lock(String lockName, long waitTime) {
		// 参数(lockName)为锁的名称，作为Redis中的key
		RLock rLock = redissonClient.getLock(lockName);
		try {
			// tryLock() 方法尝试获取锁。如果在指定时间内获取到锁返回true，否则返回false
			// 参数一：等待获取锁的最大时间（毫秒）
			// 参数二：锁的自动释放时间（leaseTime，毫秒）
			// 参数三：时间单位
			boolean isLocked = rLock.tryLock(waitTime, 30, TimeUnit.MILLISECONDS);

			// 当没有显式设置 leaseTime参数(即第二个参数)，看门狗即可生效，否则不生效。
			// boolean isLocked2 = rLock.tryLock();
			// boolean isLocked3 = rLock.tryLock(waitTime, TimeUnit.MILLISECONDS);

			if (isLocked) {
				// TODO 业务逻辑
			}
		} catch (Exception e) {
			if (rLock.isHeldByCurrentThread()) {
				rLock.unlock();
			}
		}
	}


	// TODO Redisson的看门狗机制在业务长时间阻塞时无限续期锁问题：通过线程的中断来解决
	public void fixeLock(String lockName, long waitTime) {
		// 参数(lockName)为锁的名称，作为Redis中的key
		RLock rLock = redissonClient.getLock(lockName);
		try (ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor()) {
			// 当没有显式设置 leaseTime参数(即第二个参数)，看门狗即可生效，否则不生效。
			boolean isLocked = rLock.tryLock();
			// boolean isLocked2 = rLock.tryLock(waitTime, TimeUnit.MILLISECONDS);

			Future<?> future = singleThreadExecutor.submit(() -> {
				try {
					// TODO 业务逻辑
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			});
			try {
				future.get(30, TimeUnit.SECONDS); // 设置超时时间
			} catch (TimeoutException e) {
				future.cancel(true); // 中断业务线程
				rLock.unlock();       // 释放锁
			}
		} catch (Exception e) {
			// 确保只有持有锁的线程能释放
			if (rLock.isHeldByCurrentThread()) {
				rLock.unlock();
			}
		}
	}
}
