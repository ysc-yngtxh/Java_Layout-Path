package com.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RetryAnnotationListener implements RetryListener {

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        // 在第一次重试前调用，返回 false 可以阻止执行
        log.info("【监听器】开始重试流程，当前重试次数: {}", context.getRetryCount());
        return true; 
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        // 每次重试失败后调用（包括第一次执行失败）
        log.warn("【监听器】第 {} 次重试失败，异常信息: {}", context.getRetryCount(), throwable.getMessage());
    }

    @Override
    public <T, E extends Throwable> void onSuccess(RetryContext context, RetryCallback<T, E> callback, T result) {
        // 重试最终成功后调用
        log.info("【监听器】重试最终成功，返回结果: {}", result);
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        // 整个重试流程结束后调用（无论成功或失败）
        if (throwable != null) {
            log.error("【监听器】重试流程结束，最终异常: {}", throwable.getMessage());
        } else {
            log.info("【监听器】重试流程结束，执行成功");
        }
    }

}
