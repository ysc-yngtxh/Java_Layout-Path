package com.example.client;

import com.example.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserWebClient {
   

    private final WebClient webClient;

    // 构造函数注入WebClient
    public UserWebClient() {
        this.webClient = WebClient.create("http://localhost:8080");
    }

    // 获取所有用户
    public Flux<User> getAllUsers() {
        return webClient.get()
                .uri("/api/users")
                .retrieve()
                .bodyToFlux(User.class);
    }

    // 根据ID获取用户
    public Mono<User> getUserById(Long id) {
        return webClient.get()
                .uri("/api/users/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }

    // 创建用户
    public Mono<User> createUser(User user) {
        return webClient.post()
                .uri("/api/users")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class);
    }

}
