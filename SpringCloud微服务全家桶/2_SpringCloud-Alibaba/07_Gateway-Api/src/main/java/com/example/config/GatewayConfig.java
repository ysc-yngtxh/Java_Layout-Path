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

    // TODO 使用代码Api的方式，也能进行Spring Cloud Gateway的路由配置。
    //  application.yml路由方式的优先级是大于 代码Api方式的。
    //  其实也好理解：毕竟注册到Nacos后，使用Nacos修改配置文件的路由规则会更方便
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/tb")
                        .uri("https://gitee.com/"))
                .route("host_route", r -> r.host("*.myhost.org")
                        .uri("https://httpbin.org"))
                .route("rewrite_route", r -> r.host("*.rewrite.org")
                        .filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
                        .uri("https://httpbin.org"))
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
