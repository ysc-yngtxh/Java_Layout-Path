package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
// 启用 R2DBC 仓库，指定基础包路径
@EnableR2dbcRepositories(basePackages = "com.example.repository")
public class SpringBootWebFluxFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebFluxFunctionApplication.class, args);
    }

}
