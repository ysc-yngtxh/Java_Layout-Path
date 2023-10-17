package com.example.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-17 11:02
 * @apiNote TODO
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/tb")
                        .uri("http://taobao.com"))
                .route("host_route", r -> r.host("*.myhost.org")
                        .uri("http://httpbin.org"))
                .route("rewrite_route", r -> r.host("*.rewrite.org")
                        .filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
                        .uri("http://httpbin.org"))
                // .route("hystrix_route", r -> r.host("*.hystrix.org")
                //         .filters(f -> f.hystrix(c -> c.setName("slowcmd")))
                //         .uri("http://httpbin.org"))
                // .route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
                //         .filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
                //         .uri("http://httpbin.org"))
                // .route("limit_route", r -> r
                //         .host("*.limited.org").and().path("/anything/**")
                //         .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
                //         .uri("http://httpbin.org"))
                .build();
    }
}
