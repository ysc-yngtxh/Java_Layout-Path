package com.example;

import com.example.annocation.EnableCustomBlockException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 开启openFeign功能
@EnableFeignClients
@LoadBalancerClients
// 自定义的注解：可选择开启跳转页面、返回响应流这两种模式
@EnableCustomBlockException
public class SentinelOpenFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelOpenFeignApplication.class, args);
    }

}
