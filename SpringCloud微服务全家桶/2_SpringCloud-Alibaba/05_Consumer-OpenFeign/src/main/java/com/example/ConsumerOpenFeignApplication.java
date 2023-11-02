package com.example;

import com.example.config.OpenFeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@LoadBalancerClients(defaultConfiguration = OpenFeignConfig.class) // 设置LoadBalance负载均衡策略
@EnableFeignClients  // 开启OpenFeign客户端
@EnableHystrix       // 开启熔断降级
@SpringBootApplication
public class ConsumerOpenFeignApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerOpenFeignApplication.class, args);
    }

}
