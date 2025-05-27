package com.example.route;

import com.example.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class RouterConfig {

	@Autowired
	private UserHandler userHandler;

	@Bean
	public RouterFunction<ServerResponse> route() {
		return RouterFunctions
				.route(GET("/users/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUserById)
				.andRoute(POST("/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::createUser);
	}

}
