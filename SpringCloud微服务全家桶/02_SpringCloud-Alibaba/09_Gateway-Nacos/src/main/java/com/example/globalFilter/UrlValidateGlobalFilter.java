package com.example.globalFilter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-06 20:30
 * @apiNote TODO
 */
@Component
public class UrlValidateGlobalFilter implements GlobalFilter, Ordered {

    // 全局过滤器（Global Filter）是一种在整个应用程序的所有路由中执行的过滤器。
    // 它是在 Gateway 配置中全局定义的一个过滤器，会在每个请求和响应上都被调用。
    // 全局过滤器对于一些通用的操作非常有用，例如鉴权、日志记录等。它在整个网关中生效，无需为每个路由单独配置。
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从请求中获取请求参数token的值
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        // 若token为空，则响应客户端状态码为401，未授权。否则通过验证
        if (!StringUtils.hasText(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 为当前GlobalFilter赋予最高优先级
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
