package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.retry.RetryListener;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.core.retry.Retryable;

import java.time.Duration;

@Slf4j
@Configuration
public class ResilientConfig {

    @Bean
    public RetryTemplate retryTemplate() {
        // 重试策略：最多重试 3 次（即执行 1 次 + 重试 3 次 = 总共 4 次尝试）
        RetryPolicy policy = RetryPolicy.builder()
                // 最大重试次数为 3 次
                .maxRetries(3)
                // 每次重试间隔 2 秒
                .delay(Duration.ofMillis(100))
                // 指定需要重试的异常类型
                .includes(RuntimeException.class)
                // 可选：设置重试间隔倍数（用于指数退避策略）
                .multiplier(2.0)
                .build();
        RetryTemplate retryTemplate = new RetryTemplate(policy);

        // 添加一个简单的日志监听器
        retryTemplate.setRetryListener(new RetryListener() {
            @Override
            public void beforeRetry(@NonNull RetryPolicy policy, @NonNull Retryable<?> retryable) {
                log.info("准备执行重试...");
            }

            @Override
            public void onRetryFailure(@NonNull RetryPolicy retryPolicy, @NonNull Retryable<?> retryable, @NonNull Throwable throwable) {
                log.error("重试失败", throwable);
            }
        });

        return retryTemplate;
    }
}
