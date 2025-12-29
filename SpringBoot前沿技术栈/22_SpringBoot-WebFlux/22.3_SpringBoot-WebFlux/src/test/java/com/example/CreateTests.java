package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * @author 游家纨绔
 * @dateTime 2025-12-15 00:03
 * @apiNote TODO Mono和Flux的创建方式
 */
@SpringBootTest
class CreateTests {

	@Test
	public void monoCreate() {
		// 创建包含值的Mono
		Mono<String> mono1 = Mono.just("Hello");
		// 创建空Mono
		Mono<String> mono2 = Mono.empty();
		// 创建错误Mono
		Mono<String> mono3 = Mono.error(new RuntimeException("Error"));
		// 延迟创建
		Mono<String> mono4 = Mono.defer(() -> Mono.just("Deferred"));
		// 从Callable创建
		Mono<String> mono5 = Mono.fromCallable(() -> "FromCallable");
		// 从CompletableFuture创建
		Mono<String> mono6 = Mono.fromFuture(
				CompletableFuture.supplyAsync(() -> "FromFuture")
		);

		System.out.println(mono1.block());
		System.out.println(mono4.block());
		System.out.println(mono5.block());
		System.out.println(mono6.block());
	}

	@Test
	public void fluxCreate() {
		// 从多个值创建
		Flux<Integer> flux1 = Flux.just(1, 2, 3, 4, 5);
		// 从数组创建
		Flux<String> flux2 = Flux.fromArray(new String[]{"A", "B", "C"});
		// 从集合创建
		Flux<String> flux3 = Flux.fromIterable(Arrays.asList("A", "B", "C"));
		// 范围创建
		Flux<Integer> flux4 = Flux.range(1, 10);  // 1到10
		// 间隔创建（每秒发射一个）
		Flux<Long> flux5 = Flux.interval(Duration.ofSeconds(1));
		// 生成器创建
		Flux<Integer> flux6 = Flux.generate(
				() -> 0,  // 初始状态
				(state, sink) -> {
					sink.next(state);
					if (state == 10) {
						sink.complete();
					}
					return state + 1;
				}
		                                   );

		// 编程式创建
		Flux<String> flux7 = Flux.create(sink -> {
			sink.next("A");
			sink.next("B");
			sink.next("C");
			sink.complete();
		});
	}
}
