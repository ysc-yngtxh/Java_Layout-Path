package com.example.client;

import com.example.pojo.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class UserWebClient {

    // TODO 注入WebClient实例，可以把它理解为 SpringBootWeb 的 RestTemplate
    private final WebClient webClient;

    // 构造函数注入 WebClient
    public UserWebClient() {
        // this.webClient = WebClient.create("http://localhost:8888");
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8888")
                // 设置默认 Header 参数
                .defaultHeader("Language", "zh_CN")
                // 设置默认 Cookie
                .defaultCookie("color", "blue")
                // 设置请求过滤器：携带自定义 Header 和 Attribute
                .filter((request, next) -> {
                    ClientRequest filtered = ClientRequest.from(request)
                            .header("foo", "bar")
                            .attribute("staffId", UUID.randomUUID().toString())
                            .build();
                    System.out.println("Request: " + request.url());
                    return next.exchange(filtered);
                })
                .build();
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

    // 提供 webClient 的 SSE 示例
    public Flux<String> getServerSentEvents() {
        return webClient.get()
                .uri("/api/sse/stream")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class);
    }

    // 创建用户
    public Mono<User> createUser2(User user) {
        return webClient.post()
                .uri("/api/users")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(mono -> {
                    if (mono.statusCode().is2xxSuccessful()) {
                        return mono.bodyToMono(User.class);
                    } else {
                        return Mono.error(new RuntimeException("Failed to create user"));
                    }
                }).flatMap(createdUser -> {
                    System.out.println("Created User ID: " + createdUser.getId());
                    return Mono.just(createdUser);
                });
    }

}
