package com.example.service;

import com.example.utils.RetryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Service
public class RetryProgrammingService {

    @Autowired
    private RetryTemplate retryTemplate;

    public String executeWithRetry(String input) {
        // 编程式核心：调用 execute 方法，传入 RetryCallback 和 (可选的) RecoveryCallback
        return retryTemplate.execute(
            // 1. 重试回调 (RetryCallback) —— 必须实现
            new RetryCallback<String, RuntimeException>() {
                @Override
                public String doWithRetry(RetryContext context) throws RuntimeException {
                    // context 可以获取当前重试状态
                    int currentCount = context.getRetryCount(); // 已经重试的次数（从0开始）
                    System.out.println(">>> 正在执行 doWithRetry, 当前已重试次数: " + currentCount);
                    
                    // 调用不稳定的外部服务
                    return RetryUtil.fetchData(input);
                }
            },
            // 2. 恢复回调 (RecoveryCallback) —— 可选，当所有重试耗尽后执行降级逻辑
            new RecoveryCallback<String>() {
                @Override
                public String recover(RetryContext context) throws Exception {
                    // 获取最后一次抛出的异常
                    Throwable lastThrowable = context.getLastThrowable();
                    System.err.println("!!! 所有重试已耗尽，执行降级逻辑。最后一次异常: " + lastThrowable.getMessage());
                    
                    // 返回兜底数据，避免抛出异常影响上游
                    return "【降级返回值】服务暂时不可用，请稍后再试。";
                }
            }
        );
    }
}
