package com.example;

import com.example.funnel.FunnelRateLimiter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringFunnelApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {
        FunnelRateLimiter limiter = new FunnelRateLimiter();
        int testAccessCount = 30;
        int capacity = 5;
        int allowQuota = 5;
        int perSecond = 30;
        int allowCount = 0;
        int denyCount = 0;
        for (int i = 0; i < testAccessCount; i++) {
            boolean isAllow = limiter.isActionAllowed("dadiyang", "doSomething", 5, 5, 30);
            if (isAllow) {
                allowCount++;
            } else {
                denyCount++;
            }
            System.out.println("访问权限：" + isAllow);
            Thread.sleep(1000);
        }
        System.out.println("报告：");
        System.out.println("漏斗容量：" + capacity);
        System.out.println("漏斗流动速率：" + allowQuota + "次/" + perSecond + "秒");

        System.out.println("测试次数=" + testAccessCount);
        System.out.println("允许次数=" + allowCount);
        System.out.println("拒绝次数=" + denyCount);
    }

}
