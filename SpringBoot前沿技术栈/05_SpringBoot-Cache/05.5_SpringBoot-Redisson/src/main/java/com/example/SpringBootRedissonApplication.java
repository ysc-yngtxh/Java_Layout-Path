package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SpringBootRedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedissonApplication.class, args);
    }

}
