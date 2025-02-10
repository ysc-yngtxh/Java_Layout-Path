package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class SpringRabbitRedisRetryApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringRabbitRedisRetryApplication.class, args);
    }
}

