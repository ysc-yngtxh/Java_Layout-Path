package com.example.repository;

import com.example.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * R2DBC Repository
 */
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    Flux<User> findByAgeGreaterThan(Integer age);

    Mono<User> findByEmail(String email);

    @Query("SELECT * FROM users WHERE name LIKE :name")
    Flux<User> searchByName(String name);
}
