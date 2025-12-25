package com.example;

import com.example.entity.User;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 游家纨绔
 * @dateTime 2025-12-15 00:01
 * @apiNote TODO 转换操作
 */
@SpringBootTest
public class TransferTests {

	@Test
	public void transformOperations() {
		Flux<Integer> numbers = Flux.range(1, 10);

		// map - 同步转换
		Flux<String> mapped = numbers.map(n -> "Number: " + n);

		// flatMap - 异步转换（返回Publisher）
		Flux<String> flatMapped = numbers.flatMap(n ->
				                                          Mono.just("Number: " + n).delayElement(Duration.ofMillis(100))
		                                         );

		// concatMap - 顺序异步转换
		Flux<String> concatMapped = numbers.concatMap(n ->
				                                              Mono.just("Number: " + n).delayElement(Duration.ofMillis(100))
		                                             );

		// filter - 过滤
		Flux<Integer> filtered = numbers.filter(n -> n % 2 == 0);

		// take - 取前N个
		Flux<Integer> taken = numbers.take(5);

		// skip - 跳过前N个
		Flux<Integer> skipped = numbers.skip(3);

		// distinct - 去重
		Flux<Integer> distinct = Flux.just(1, 1, 2, 2, 3, 3).distinct();

		// sort - 排序
		Flux<Integer> sorted = Flux.just(3, 1, 2).sort();
	}

	@Test
	public void monoTransform() {
		Mono<User> userMono = Mono.just(new User(1L, "张三", "test@test.com", 25));
		// map
		Mono<String> nameMono = userMono.map(User::getName);
		// flatMap
		Mono<String> detailMono = userMono.flatMap(
				user -> fetchUserDetails(user.getId()));
		// zipWith - 合并两个Mono
		Mono<String> combined = userMono.zipWith(
				Mono.just("额外信息"),
				(user, extra) -> user.getName() + " - " + extra
		                                        );
	}

	private Mono<String> fetchUserDetails(Long id) {
		return Mono.just("User details for " + id);
	}
}
