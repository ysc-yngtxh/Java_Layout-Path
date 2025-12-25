package com.example;

import com.example.handler.UserHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

@SpringBootTest
class SpringBootWebFluxH2ApplicationTests {

	@Autowired
	private UserHandler userHandler;

	@Test
	void contextLoads() {
		RouterFunction<ServerResponse> route = RouterFunctions
				.route(RequestPredicates.GET("/flux/users/all")
				                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				       userHandler::getAllUsers);

		HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
		HttpServer httpServer = HttpServer.create().host("localhost").port(8080);
		httpServer.handle(adapter).bindNow();
	}


}
