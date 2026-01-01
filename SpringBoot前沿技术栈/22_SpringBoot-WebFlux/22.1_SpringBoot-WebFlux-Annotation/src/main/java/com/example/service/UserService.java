package com.example.service;

import com.example.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserService {
   
    Flux<User> findAllFlux();
    void flux2ListAsync(Flux<User> userFlux);
    Mono<User> findAllMono();
    Mono<User> findById(Integer id);
    Mono<User> findByUsername(String username);
    Mono<User> save(User user);
    Mono<Void> saveProcess(User user);
    Mono<User> update(Integer id, User user);
    Mono<Void> deleteById(Integer id);
}
