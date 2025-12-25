package com.example.service;

import com.example.pojo.Users;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
   
    Flux<Users> findAll();
    Mono<Users> findById(Integer id);
    Mono<Users> save(Users users);
    Mono<Users> update(Integer id, Users users);
    Mono<Void> deleteById(Integer id);
    Mono<Users> findByUsername(String username);
}
