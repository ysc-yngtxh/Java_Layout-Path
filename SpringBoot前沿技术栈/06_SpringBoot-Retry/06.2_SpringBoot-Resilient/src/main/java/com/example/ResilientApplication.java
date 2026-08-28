package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;

// 在Spring Framework 7 版本之前，重试功能需要手动添加 spring-retry 依赖。
// 现在，重试功能已内置于 Spring 框架核心，开箱即用，无需额外引入。
// 启用重试注解从 @EnableRetry 变为 @EnableResilientMethods
@EnableResilientMethods
@SpringBootApplication
public class ResilientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResilientApplication.class, args);
    }

}
