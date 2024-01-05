package com.example.filterFactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-05 14:31
 * @apiNote TODO
 */
@Component
@Slf4j
public class OneGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            // pre-filter “预”过滤器逻辑
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            log.info(config.getName() + "-" + config.getValue() + " - pre 开始执行");
            return chain.filter(exchange).then(
                    // post-filter “预”过滤器逻辑都会被执行
                    // 创建一个Mono对象，它表示一个延迟执行的任务。
                    Mono.fromRunnable(() -> {
                        stopWatch.stop();
                        log.info(config.getName() + "-" + config.getValue()
                                + " - post 执行完毕。该filter执行链总用时(毫秒)：" + stopWatch.getLastTaskTimeMillis());
                    })
            );
        };
    }
}
