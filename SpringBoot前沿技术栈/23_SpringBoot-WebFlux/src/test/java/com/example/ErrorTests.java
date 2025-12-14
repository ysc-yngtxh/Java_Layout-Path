package com.example;

import com.example.entity.User;
import com.example.repository.UserRepository;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

/**
 * @author 游家纨绔
 * @dateTime 2025-12-15 00:02
 * @apiNote TODO 错误操作
 */
@SpringBootTest
public class ErrorTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void errorHandling() {
		Flux<Integer> flux = Flux.just(1, 2, 0, 4, 5)
		                         .map(n -> 10 / n);  // 会在0处抛出异常

		// onErrorReturn - 返回默认值
		flux.onErrorReturn(-1)
		    .subscribe(System.out::println);
		// 结果: 10, 5, -1

		// onErrorResume - 返回备用Publisher
		flux.onErrorResume(e -> Flux.just(-1, -2, -3))
		    .subscribe(System.out::println);

		// onErrorMap - 转换异常
		flux.onErrorMap(ArithmeticException.class,
		                e -> new BusinessException("除零错误"))
		    .subscribe(System.out::println);

		// doOnError - 记录错误（不处理）
		flux.doOnError(e -> System.err.println("发生错误: " + e.getMessage()))
		    .subscribe(System.out::println);

		// retry - 重试
		flux.retry(3)  // 重试3次
		    .subscribe(System.out::println);

		// retryWhen - 高级重试策略
		flux.retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
		                    .maxBackoff(Duration.ofSeconds(10)))
		    .subscribe(System.out::println);
	}

	public Mono<User> getUser(Long id) {
		return userRepository.findById(id)
		                     .switchIfEmpty(Mono.error(new UserNotFoundException(id)))
		                     .onErrorResume(e -> {
			                     if (e instanceof UserNotFoundException) {
				                     return Mono.just(new User(-1L, "默认用户", "", 0));
			                     }
			                     return Mono.error(e);
		                     });
	}

	class BusinessException extends RuntimeException {
		public BusinessException(String message) {
			super(message);
		}
	}

	class UserNotFoundException extends RuntimeException {
		public UserNotFoundException(Long id) {
			super("用户不存在: " + id);
		}
	}

}
