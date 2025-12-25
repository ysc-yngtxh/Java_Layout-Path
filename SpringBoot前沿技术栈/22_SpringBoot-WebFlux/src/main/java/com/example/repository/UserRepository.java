package com.example.repository;

import com.example.pojo.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<User, Integer> {
   
    // 根据用户名查询用户
    Mono<User> findByUsername(String username);

    // 根据邮箱查询用户
    Mono<User> findByEmail(String email);
}
