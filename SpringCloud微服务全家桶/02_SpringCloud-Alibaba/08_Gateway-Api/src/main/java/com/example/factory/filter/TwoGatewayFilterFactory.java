package com.example.factory.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-05 14:30
 * @apiNote TODO
 */
@Component
@Slf4j
public class TwoGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            // pre-filter
            log.info(config.getName() + "-" + config.getValue() + " - pre 开始执行");
            return chain.filter(exchange).then(
                    // post-filter
                    Mono.fromRunnable(() -> {
                        log.info(config.getName() + "-" + config.getValue() + " - post 执行完毕。");
                    })
            );
        };
    }
}
