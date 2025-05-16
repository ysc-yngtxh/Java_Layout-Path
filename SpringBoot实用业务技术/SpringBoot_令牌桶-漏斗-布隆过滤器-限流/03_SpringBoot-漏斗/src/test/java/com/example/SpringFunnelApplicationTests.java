package com.example;

import com.example.funnel.FunnelRateLimiter;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringFunnelApplicationTests {

	/**
	 * 在处理突发的大流量请求时候，令牌桶算法效率是优于漏斗算法的。
	 * 因为在一瞬间大量请求过来，漏斗算法也还是只会根据自己的水滴恒定速率处理请求，你再怎么急请求量再怎么大也得给我乖乖排队。
	 * 并且就算你排队了，一旦队列容量满了，后续请求也会直接丢弃。
	 * 而令牌桶算法可以在一瞬间把桶里的令牌都消耗掉，支持一瞬间处理大量请求。所以面对这种场景下，令牌桶算法相对来说比较优秀。
	 */
	@Test
	void contextLoads() throws InterruptedException {
		FunnelRateLimiter limiter = new FunnelRateLimiter();
		int testAccessCount = 30;  // 测试次数
		int capacity = 5;          // 漏斗容量
		int allowQuota = 5;        // 单位时间内水滴流量
		int perSecond = 30;        // 单位时间（秒）
		int allowCount = 0;        // 允许访问次数
		int denyCount = 0;         // 拒绝访问次数
		for (int i = 0; i < testAccessCount; i++) {
			// 调用漏斗算法，判断当前循环是否允许访问（每一次循环可当成一次请求）
			boolean isAllow = limiter.isActionAllowed("小曹", "提交订单", 5, 5, 10);
			if (isAllow) {
				allowCount++;
			} else {
				denyCount++;
			}
			System.out.println("访问权限：" + isAllow);
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println("报告：");
		System.out.println("漏斗容量：" + capacity);
		System.out.println("漏斗流动速率：" + allowQuota + "次/" + perSecond + "秒");

		System.out.println("测试次数=" + testAccessCount);
		System.out.println("允许次数=" + allowCount);
		System.out.println("拒绝次数=" + denyCount);
	}
}
