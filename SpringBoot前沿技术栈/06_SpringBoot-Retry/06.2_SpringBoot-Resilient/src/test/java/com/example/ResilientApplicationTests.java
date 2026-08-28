package com.example;

import com.example.service.ResilientAnnotationService;
import com.example.service.ResilientProgrammingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ResilientApplicationTests {

    /**
     * Spring Resilient 声明式进行重试机制
     */
    @Autowired
    private ResilientAnnotationService resilientAnnotationService;

    // 这种就是与应用层相联系的重试机制
    @Test
    public void retry() {
        boolean result = resilientAnnotationService.calls("abc");
        log.info("----结果是: {} --", result);
    }



    /**
     * Spring Resilient 编程式进行重试机制
     */
    @Autowired
    private ResilientProgrammingService resilientProgrammingService;

    // 这种就是与应用层相联系的重试机制
    @Test
    public void retry2() {
        System.out.println("\n========== 开始测试 RetryTemplate ==========");
        String result = resilientProgrammingService.executeWithRetry("test-id-001");
        System.out.println("最终业务结果: " + result);
    }
}
