package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// @EnableDiscoveryClient 来源于 spring-cloud-commons，支持多种注册中心（如 Nacos, Consul, Eureka）。合适于保持灵活性，未来可能更换注册中心的场景。
// 并且在当前服务中，该注解并不是必须的，只要在项目中引入了 eureka-client 依赖，Spring Boot 的自动配置（Auto-Configuration）机制就会自动启用 Eureka 客户端功能。
@EnableDiscoveryClient // 可省略
@SpringBootApplication
public class EurekaClientExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientExecutorApplication.class, args);
    }

}
