package com.example.service;

import com.example.utils.ResilientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResilientAnnotationService {

    // ⚠️：Spring Framework 7 为了功能更聚焦，设计上不包含降级（fallback）逻辑。对应 Spring Retry的 @Recover 注解，Spring Framework 7 不再提供类似的功能。
    // 重试配置：遇到 NumberFormatException、IllegalArgumentException 异常时重试 3 次，间隔 2s、4s、8s 进行重试
	@Retryable(
            value = {NumberFormatException.class, IllegalArgumentException.class},  // 指定触发重试的异常
            maxRetries = 3, // 最大重试次数（不含首次调用）
            delay = 2000L,  // 初始延迟时间，单位毫秒
            multiplier = 2  // 重试间隔，指数级增长
    )
	public boolean calls(String param) {
		return ResilientUtil.ResilientTask();
	}

}
