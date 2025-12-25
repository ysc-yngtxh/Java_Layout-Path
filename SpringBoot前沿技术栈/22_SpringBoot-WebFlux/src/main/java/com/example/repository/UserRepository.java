package com.example.repository;

import com.example.pojo.Users;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<Users, Integer> {
   
    // 根据用户名查询用户
    Mono<Users> findByUserName(String username);

    // 根据邮箱查询用户
    Mono<Users> findByEmail(String email);
}
