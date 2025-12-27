package com.example.route;

import com.example.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {

    // 函数式编程风格的路由配置，区别于注解风格的路由配置
    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler userHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/api/users")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        userHandler::getAllUsers)

                .andRoute(RequestPredicates.GET("/api/users/queryParam")
                                .and(RequestPredicates.queryParam("created_date", v->!v.isEmpty()))
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        userHandler::getAllUsers)

                .andRoute(RequestPredicates.GET("/api/users/{id}")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        userHandler::getUserById)

                .andRoute(RequestPredicates.POST("/api/users")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        userHandler::createUser)

                .andRoute(RequestPredicates.PUT("/api/users/{id}")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        userHandler::updateUser)

                .andRoute(RequestPredicates.DELETE("/api/users/{id}")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        userHandler::deleteUser);
    }

}
