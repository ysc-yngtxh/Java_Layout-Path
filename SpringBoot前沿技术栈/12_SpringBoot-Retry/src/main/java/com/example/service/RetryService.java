package com.example.service;

import com.example.utils.RetryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryService {

    /**
     * @Backoff注解 重试补偿策略：
     *     不设置参数时，默认使用FixedBackOffPolicy（指定等待时间），重试等待1000ms
     *     设置delay,使用FixedBackOffPolicy（指定等待- - 设置delay和maxDealy时，重试等待在这两个值之间均态分布
     *     设置delay、maxDealy、multiplier，使用 ExponentialBackOffPolicy（指数级重试间隔的实现 ），
     *     multiplier即指定延迟倍数，比如delay=5000l,multiplier=2,则第一次重试为5秒，第二次为10秒，第三次为20秒
     */
    // 启动重试机制的异常RemoteAccessException、IllegalArgumentException。最大重连次数3。重连所花时间。
    @Retryable(value = {RemoteAccessException.class, IllegalArgumentException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 2))
    public boolean calls(String param){
        return RetryUtil.retryTask("abc");
    }

    // 这里,当抛出RemoteAccessException时重试会尝试运行。
    // 当@Retryable方法因指定异常而失败时，@Recover注释用于在@Retryable方法因指定的异常而失败时定义单独的恢复方法
    // recover()方法返回值需要与重试方法返回值保证一致
    @Recover
    public boolean recover(Exception e, String param){
        log.error("达到最大重试次数，或抛出了一个没有指定进行重试的异常 {}", e);
        return false;
    }
}