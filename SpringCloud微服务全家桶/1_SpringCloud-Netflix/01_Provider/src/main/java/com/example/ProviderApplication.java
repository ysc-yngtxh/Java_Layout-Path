package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 游家纨绔
 */
// 这个注解是用来指定使用 Eureka注册中心
// 如果注册中心不是Eureka，使用其他注册中心怎么办？使用 @EnableDiscoveryClient 注解响应注册中心，相当于各种注册中心的统一注册。
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.example.mapper")
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
