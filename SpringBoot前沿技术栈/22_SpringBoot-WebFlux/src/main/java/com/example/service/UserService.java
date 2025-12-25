package com.example.service;

import com.example.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
   
    Flux<User> findAll();
    Mono<User> findById(Integer id);
    Mono<User> save(User user);
    Mono<User> update(Integer id, User user);
    Mono<Void> deleteById(Integer id);
    Mono<User> findByUsername(String username);
}
