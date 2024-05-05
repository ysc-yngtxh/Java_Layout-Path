package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.dao")
public class CompletableFutureApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompletableFutureApplication.class, args);
    }

}
