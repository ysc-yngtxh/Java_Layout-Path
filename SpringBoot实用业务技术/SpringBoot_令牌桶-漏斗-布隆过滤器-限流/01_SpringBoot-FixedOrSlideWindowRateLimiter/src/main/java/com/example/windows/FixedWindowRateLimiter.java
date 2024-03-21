package com.example.windows;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class FixedWindowRateLimiter {
    // 时间窗口大小，单位毫秒
    private long windowSize;
    // 允许通过请求数
    private int maxRequestCount;
    // 窗口右边界
    private long windowBorder;
    // 当前窗口通过的请求计数
    private AtomicInteger count = new AtomicInteger(0);

    public FixedWindowRateLimiter(long windowSize, int maxRequestCount) {
        this.windowSize = windowSize;
        this.maxRequestCount = maxRequestCount;
        windowBorder = System.currentTimeMillis() + windowSize;
    }

    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        if (windowBorder < currentTime) {
            log.info("window reset");
            do {
                windowBorder += windowSize;
            } while (windowBorder < currentTime);
            count = new AtomicInteger(0);
        }

        if (count.intValue() < maxRequestCount) {
            count.incrementAndGet();
            log.info("tryAcquire success");
            return true;
        } else {
            log.info("tryAcquire fail");
            return false;
        }
    }
}