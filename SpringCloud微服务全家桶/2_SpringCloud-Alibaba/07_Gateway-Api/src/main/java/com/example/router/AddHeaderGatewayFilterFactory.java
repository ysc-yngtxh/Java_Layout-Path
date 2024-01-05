package com.example.router;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-05 12:20
 * @apiNote TODO AddHeader自定义路由断言规则
 */
@Component
public class AddHeaderGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            ServerHttpRequest changedRequest = exchange.getRequest()
                    .mutate()
                    .header(config.getName(), config.getValue())
                    .build();

            ServerWebExchange changedExchange = exchange.mutate()
                    .request(changedRequest)
                    .build();

            return chain.filter(changedExchange);
        };
    }
}