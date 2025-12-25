package com.example.config;

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

	@Bean
	public RouterFunction<ServerResponse> userRoutes(UserHandler userHandler) {
		return RouterFunctions
				.route(RequestPredicates.GET("/flux/users/all")
				                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				       userHandler::getAllUsers)
				.andRoute(RequestPredicates.GET("/flux/users/{id}")
				                           .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				          userHandler::getUserById)
				.andRoute(RequestPredicates.POST("/flux/users")
				                           .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				          userHandler::createUser)
				.andRoute(RequestPredicates.PUT("/flux/users/{id}")
				                           .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				          userHandler::updateUser)
				.andRoute(RequestPredicates.DELETE("/flux/users/{id}")
				                           .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				          userHandler::deleteUser);
	}

}
