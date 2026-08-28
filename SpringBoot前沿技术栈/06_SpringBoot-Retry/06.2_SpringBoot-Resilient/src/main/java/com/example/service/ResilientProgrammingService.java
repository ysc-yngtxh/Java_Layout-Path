package com.example.service;

import com.example.utils.ResilientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResilientProgrammingService {

    @Autowired
    private RetryTemplate retryTemplate;

    public String executeWithRetry(String input) {
        // 编程式核心
        try {
            return retryTemplate.invoke(() -> ResilientUtil.fetchData(input));
        } catch (Exception e) {
            // 所有重试都失败后，执行降级逻辑
            log.error("调用外部API最终失败，执行降级逻辑", e);
            return "fallback result";
        }
    }
}
