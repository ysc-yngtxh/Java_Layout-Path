package com.example.handler;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<User> user = userService.getUserById(Integer.parseInt(id));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(user, User.class);
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class).flatMap(userService::createUser);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(user, User.class);
    }

}
