package com.example;

import com.example.config.OpenFeignConfig;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@LoadBalancerClients(defaultConfiguration = OpenFeignConfig.class) // 设置LoadBalance负载均衡策略
@EnableFeignClients  // 开启OpenFeign客户端
@EnableDiscoveryClient(autoRegister = false) // 关闭自动注册到注册中心Nacos，默认为true
// 换句话说，如果我们不想该服务注册到注册中心，只是想从注册中心拉取服务，就可以设置 (autoRegister = false)
@SpringBootApplication
public class ConsumerOpenFeignApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // 开启并配置OpenFeign的日志级别
    @Bean
    Logger.Level feignLoggerLevel() {
        // FULL是详细日志
        return Logger.Level.FULL;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerOpenFeignApplication.class, args);
    }

}
