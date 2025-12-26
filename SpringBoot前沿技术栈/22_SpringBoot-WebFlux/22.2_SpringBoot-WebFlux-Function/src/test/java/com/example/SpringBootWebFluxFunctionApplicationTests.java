package com.example;

import com.example.handler.UserHandler;
import com.example.pojo.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpringBootWebFluxFunctionApplicationTests {

    @Autowired
    private UserHandler userHandler;

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRepository userRepository;

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

    @Test
    public void testGetAllUsers() {
        User user1 = new User(1, "user1", "user1@example.com", "User One", (byte) 0);
        User user2 = new User(2, "user2", "user2@example.com", "User Two", (byte) 1);

        when(userRepository.findAll()).thenReturn(Flux.just(user1, user2));

        // 该段代码意思是：
        //     测试 GET 请求 /api/users 接口，期望返回状态码 200 OK，响应头的内容类型为 application/json，
        //     响应体是一个包含两个 User 对象的列表，并且列表中包含 user1 和 user2。
        webTestClient.get().uri("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(User.class)
                .hasSize(2)
                .contains(user1, user2);
    }

    @Test
    public void testCreateUser() {
        User user = new User(null, "newuser", "newuser@example.com", "New User", (byte) 1);
        User savedUser = new User(3, "newuser", "newuser@example.com", "New User", (byte) 0);

        when(userRepository.save(any(User.class))).thenReturn(Mono.just(savedUser));

        // 该段代码意思是：
        //     测试 POST 请求 /api/users 接口，发送一个新的 User 对象作为请求体，
        //     期望返回状态码 201 Created，响应体是保存后的 User 对象，并且该对象与 savedUser 相等。
        webTestClient.post().uri("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(User.class)
                .isEqualTo(savedUser);
    }

}
