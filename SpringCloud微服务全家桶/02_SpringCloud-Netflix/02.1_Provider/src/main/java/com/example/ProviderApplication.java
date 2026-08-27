package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 游家纨绔
 */
// @EnableDiscoveryClient注解是用来注册中心的统一注册，不限于Eureka，还包含其他的注册中心。
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.example.mapper")
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
