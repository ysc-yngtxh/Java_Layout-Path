package com.example;

import com.example.config.OpenFeignConfig;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients  // 开启OpenFeign客户端
@LoadBalancerClients(defaultConfiguration = OpenFeignConfig.class) // 设置LoadBalance负载均衡策略
// 如果不想将该服务注册到注册中心，只是想从注册中心拉取服务，可以设置 (autoRegister = false)
@EnableDiscoveryClient(autoRegister = false) // 关闭自动注册到注册中心Nacos，默认为true
public class NacosDiscoveryOpenFeignApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    // 开启并配置OpenFeign的日志级别
    @Bean
    Logger.Level feignLoggerLevel() {
        // FULL是详细日志
        return Logger.Level.FULL;
    }

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryOpenFeignApplication.class, args);
    }

}
