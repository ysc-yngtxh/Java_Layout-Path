package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 开启openFeign功能
@EnableFeignClients
// @LoadBalancerClients
public class SentinelFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelFeignApplication.class, args);
    }

}
