package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfig {

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate template = new RetryTemplate();

        // 1. 重试策略：最多重试 3 次（即执行 1 次 + 重试 3 次 = 总共 4 次尝试）
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(4);
        template.setRetryPolicy(retryPolicy);  // 将重试策略设置到 RetryTemplate 中

        // 2. 退避策略：每次重试固定间隔 2 秒
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(2000L);    // 单位毫秒
        template.setBackOffPolicy(backOffPolicy); // 将退避策略设置到 RetryTemplate 中

        // 3. 注册重试监听器
        template.registerListener(new RetryListener() {
            @Override
            public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
                // 第一次执行前触发，返回 false 可阻止执行
                System.out.println("[Listener] 开启重试上下文，次数: " + context.getRetryCount());
                return true;
            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
                // 每次发生错误重试前触发（用于记录错误日志）
                System.err.println("[Listener] 第 " + (context.getRetryCount() + 1) + " 次执行出错: " + throwable.getMessage());
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
                // 最终结束时触发（无论成功还是失败/降级）
                if (throwable != null) {
                    System.err.println("[Listener] 重试结束，最终异常: " + throwable.getMessage());
                } else {
                    System.out.println("[Listener] 重试结束，执行成功！");
                }
            }
        });
        
        return template;
    }
}
