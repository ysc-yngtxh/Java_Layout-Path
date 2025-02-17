package com.example.spring;

import com.example.service.RetryService;
import com.example.utils.RetryUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class SpringRetryApplicationTests {

    // 重试时间间隔
    private long fixedPeriodTime = 1000L;

    // 最大重试次数， 默认为3
    private int maxRetryTimes = 3;

    // 表示那些异常需要重试， key表示异常的字节码， value为true表示需要重试
    private Map<Class<? extends Throwable>, Boolean> exceptionMap = new HashMap<>();

    // 这种就是纯代码编写重试规则
    @Test
    void contextLoads() {
        // FixedBackOffPolicy 是 Spring Retry 模块提供的一个类，用于在重试失败的操作时，控制每次重试之间的等待时间（退避策略）。
        // 设置重试回退策略，主要设置重试时间间隔
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(fixedPeriodTime);

        // 设置重试策略，主要设置重试次数, 以及什么异常进行重试
        exceptionMap.put(NumberFormatException.class, true);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(maxRetryTimes, exceptionMap);

        // 构建重试模板实列
        RetryTemplate retryTemplate = new RetryTemplate();

        // 设置重试模板的重试策略
        retryTemplate.setRetryPolicy(retryPolicy);
        // 设置重试模板的重试回退策略
        retryTemplate.setBackOffPolicy(backOffPolicy);

        Boolean execute = retryTemplate.execute(
                retryContext -> {
                    boolean b = RetryUtil.retryTask();
                    log.info("调用结果: {}", b);
                    return b;
                },
                retryContext -> {
                    log.info("已经达到最大重试次数或抛出了不重试的异常");
                    return false;
                });
        log.info("执行结果: {}", execute);
    }


    /**
     * Spring-Retry  注解的方式进行重试机制
     * 注意事项
     *     1、异常类型需要与Recover方法参数类型保持一致，且重试方法第一个参数必须为Throwable或其子类，否则找不到重试回调的方法；
     *     2、recover方法返回值需要与重试方法返回值保证一致
     */
    @Autowired
    private RetryService retryService;

    // 这种就是与应用层相联系的重试机制
    @Test
    public void retry(){
        boolean result = retryService.calls("abc");
        log.info("----结果是: {} --", result);
    }

}
