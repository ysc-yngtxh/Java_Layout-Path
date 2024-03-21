package com.example;

import com.example.windows.FixedWindowRateLimiter;
import com.example.windows.SlidingWindowRateLimiter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class WindowsApplicationTests {

    // 固定窗口算法：通过在单位时间内维护一个计数器，能够限制在每个固定的时间段内请求通过的次数，以达到限流的效果。

    // 固定窗口算法的优点是实现简单，但是可能无法应对突发流量的情况。
    // 比如每秒允许放行100个请求，但是在0.9秒前都没有请求进来，这就造成了在0.9秒到1秒这段时间内要处理100个请求，
    // 而在1秒到1.1秒间可能会再进入100个请求，这就造成了要在0.2秒内处理200个请求，这种流量激增就可能导致后端服务出现异常。

    @Test
    @SneakyThrows
    void contextLoads() {
        FixedWindowRateLimiter fixedWindowRateLimiter = new FixedWindowRateLimiter(1000, 5);
        for (int i = 0; i < 10; i++) {
            if (fixedWindowRateLimiter.tryAcquire()) {
                System.out.println("执行任务");
            } else {
                System.out.println("被限流");
                TimeUnit.MILLISECONDS.sleep(300);
            }
        }
    }


    // 滑动窗口算法：在固定窗口的基础上，进行了一定的升级改造。
    // 它的算法的核心在于将时间窗口进行了更精细的分片，将固定窗口分为多个小块，每次仅滑动一小块的时间。
    // 并且在每个时间段内都维护了单独的计数器，每次滑动时，都减去前一个时间块内的请求数量，并再添加一个新的时间块到末尾，
    // 当时间窗口内所有小时间块的计数器之和超过了请求阈值时，就会触发限流操作。

    // 滑动窗口算法通过将时间片进行分片，对流量的控制更加精细化，
    // 但是相应的也会浪费一些存储空间，用来维护每一块时间内的单独计数，并且还没有解决固定窗口中可能出现的流量激增问题。
    @Test
    void test() throws InterruptedException {
        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(1000, 10, 10);
        TimeUnit.MILLISECONDS.sleep(800);

        for (int i = 0; i < 15; i++) {
            boolean acquire = slidingWindowRateLimiter.tryAcquire();
            if (acquire) {
                System.out.println("执行任务");
            } else {
                System.out.println("被限流");
            }
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
}
