package com.example.service;

import com.example.utils.RetryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryAnnotationService {

	/**
	 * @Backoff注解 重试补偿策略：
	 *   不设置参数时，默认使用FixedBackOffPolicy（指定等待时间），重试等待1000ms
	 *   设置delay，使用FixedBackOffPolicy（指定等待- - 设置delay和maxDealy时，重试等待在这两个值之间均态分布
	 *   设置delay、maxDealy、multiplier，使用 ExponentialBackOffPolicy（指数级重试间隔的实现），
	 *   multiplier即指定延迟倍数，比如：delay=5000L，multiplier=2，则第一次重试为5秒，第二次为10秒，第三次为20秒
	 */
    // 重试配置：遇到 NumberFormatException、IllegalArgumentException 异常时重试 3 次，间隔 2s、4s、8s 进行重试
	@Retryable(
            retryFor = {NumberFormatException.class, IllegalArgumentException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000L, multiplier = 2),
            listeners = {"retryAnnotationListener"}
    )
	public boolean calls(String param) {
		return RetryUtil.retryTask();
	}

    // 降级补偿方法：当 @Retryable 注解标注的方法重试全部失败后，定义的降级补偿方法即为 @Recover注解 所在方法。
	// 注意：
    // 1、@Recover 方法返回值 需要与 @Retryable 方法返回值 保证一致
    // 2、@Recover 方法的第一个参数必须是 Throwable 或其子类（用于匹配具体的失败原因）。后面的参数需要与 @Retryable 方法的参数一一对应（或是空参数）。
    // 3、@Recover 补偿方法必须定义在标注 @Retryable 方法的同一个 Spring Bean 中。
	@Recover
	public boolean recover(Exception e, String param) {
		log.error("参数：{} 的方法达到最大重试次数，或抛出了一个没有指定进行重试的异常 {}", param, e);
		return false;
	}

}
