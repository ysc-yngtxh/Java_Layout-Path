package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer   // 这个注解表示的是 启用Eureka服务
@SpringBootApplication
public class EurekaServerExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerExecutorApplication.class, args);
    }

}
