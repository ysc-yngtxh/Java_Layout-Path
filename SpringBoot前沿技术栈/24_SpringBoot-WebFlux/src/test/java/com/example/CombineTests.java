package com.example;

import com.example.entity.User;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 游家纨绔
 * @dateTime 2025-12-15 00:00
 * @apiNote TODO 组合操作
 */
public class CombineTests {

	@Test
	public void combineOperations() {
		Flux<Integer> flux1 = Flux.just(1, 2, 3);
		Flux<Integer> flux2 = Flux.just(4, 5, 6);

		// concat - 顺序连接
		Flux<Integer> concat = Flux.concat(flux1, flux2);
		// 结果: 1, 2, 3, 4, 5, 6

		// merge - 并行合并
		Flux<Integer> merge = Flux.merge(flux1, flux2);
		// 结果: 交错（取决于发射时间）

		// zip - 配对合并
		Flux<String> zip = Flux.zip(flux1, flux2, (a, b) -> a + "+" + b);
		// 结果: "1+4", "2+5", "3+6"

		// combineLatest - 最新值组合
		Flux<String> combineLatest = Flux.combineLatest(
				flux1, flux2, (a, b) -> a + ":" + b
		                                               );
	}

	@Test
	public void monoZip() {
		Mono<String> name = Mono.just("张三");
		Mono<Integer> age = Mono.just(25);
		Mono<String> email = Mono.just("zhangsan@example.com");

		// 合并多个Mono
		Mono<User> userMono = Mono.zip(name, age, email)
		                          .map(tuple -> new User(null, tuple.getT1(), tuple.getT3(), tuple.getT2()));

		// 使用zipWith
		Mono<String> combined = name.zipWith(age, (n, a) -> n + " - " + a);
	}
}
