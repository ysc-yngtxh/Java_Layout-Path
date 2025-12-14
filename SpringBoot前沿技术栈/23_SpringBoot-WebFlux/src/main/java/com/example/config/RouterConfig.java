package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 函数式路由配置
 */
@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler handler) {
        return RouterFunctions.route()
                              // GET /api/users/{id}
                              .GET("/api/users/{id}", handler::getUser)
                              // GET /api/users
                              .GET("/api/users", handler::getAllUsers)
                              // POST /api/users
                              .POST("/api/users", handler::createUser)
                              // PUT /api/users/{id}
                              .PUT("/api/users/{id}", handler::updateUser)
                              // DELETE /api/users/{id}
                              .DELETE("/api/users/{id}", handler::deleteUser)
                              // 嵌套路由
                              .path("/api/admin", builder -> builder
                                      .GET("/stats", handler::getStatus)
                                      .POST("/batch", handler::batchCreate)
                              )
                              .build();
    }
}
