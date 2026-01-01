package com.example.route;

import com.example.handler.UserHandler;
import com.example.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

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




    // 基于函数式编程模型，有两个核心接口：
    //        RouterFunction（实现路由功能，请求转发给对应的handler）
    //        HandlerFunction（处理请求生成响应的函数）。
    // 核心任务定义两个函数式接口的实现并且启动需要的服务器。
    public static void main(String[] args) {
        // 创建路由
        RouterFunction<ServerResponse> route = RouterFunctions
                .route(RequestPredicates.GET("/user/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        , userHandler::getById)
                .andRoute(RequestPredicates.GET("/users").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        , userHandler::getAll);
        // 路由和handler适配
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        // 创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();
    }

}
