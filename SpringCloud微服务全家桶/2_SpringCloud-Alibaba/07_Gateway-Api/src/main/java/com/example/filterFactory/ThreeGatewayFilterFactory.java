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
public class ThreeGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            // pre-filter
            log.info(config.getName() + "-" + config.getValue() + " - pre 开始执行");
            return chain.filter(exchange).then(
                    // post-filter
                    Mono.fromRunnable(() -> {
                        log.info(config.getName() + "-" + config.getValue()
                                + " - post 执行完毕。");
                    })
            );
        };
    }
}
