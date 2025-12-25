package com.example;

import com.example.controller.UserController;
import com.example.pojo.User;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(UserController.class)
public class UserControllerTest {
   

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        User user1 = new User(1, "user1", "user1@example.com", "User One", (byte) 0);
        User user2 = new User(2, "user2", "user2@example.com", "User Two", (byte) 1);

        when(userService.findAll()).thenReturn(Flux.just(user1, user2));

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

        when(userService.save(any(User.class))).thenReturn(Mono.just(savedUser));

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
