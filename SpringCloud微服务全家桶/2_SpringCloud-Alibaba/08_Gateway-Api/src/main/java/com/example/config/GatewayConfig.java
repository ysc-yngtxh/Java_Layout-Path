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
    //  优先级：对于相同的filter工厂，在不同位置设置了不同的值，则优先级为：
    //         1、API式的filter 优先级高于 yml文件配置式filter。
    //            为了让开发人员能够在运行时动态地修改和控制API路由和过滤器的行为，而不需要重新部署整个应用程序。
    //         2、yml文件配置式局部filter 优先级高于 默认filter(default-filters)。可以更好的细粒化控制过滤逻辑。
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
                // .route("limit_route", r -> route
                //         .host("*.limited.org").and().path("/anything/**")
                //         .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
                //         .uri("http://httpbin.org"))
                .build();
    }
}
