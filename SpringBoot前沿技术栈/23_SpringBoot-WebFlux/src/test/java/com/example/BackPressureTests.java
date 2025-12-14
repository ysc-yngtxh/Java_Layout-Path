package com.example;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * @author 游家纨绔
 * @dateTime 2025-12-15 00:04
 * @apiNote TODO 背压操作
 */
@SpringBootTest
public class BackPressureTests {

	@Test
	public void backpressure() {
		Flux<Integer> flux = Flux.range(1, 1000);

		// onBackpressureBuffer - 缓冲
		flux.onBackpressureBuffer(100)
		    .subscribe(new BaseSubscriber<Integer>() {
			    @Override
			    protected void hookOnSubscribe(Subscription subscription) {
				    request(10);  // 初始请求10个
			    }

			    @Override
			    protected void hookOnNext(Integer value) {
				    System.out.println("处理: " + value);
				    request(1);  // 每处理完一个请求一个
			    }
		    });

		// onBackpressureDrop - 丢弃
		flux.onBackpressureDrop(dropped -> System.out.println("丢弃: " + dropped))
		    .subscribe();

		// onBackpressureLatest - 只保留最新
		flux.onBackpressureLatest()
		    .subscribe();

		// limitRate - 限制速率
		flux.limitRate(10)  // 每次最多请求10个
		    .subscribe();
	}
}
