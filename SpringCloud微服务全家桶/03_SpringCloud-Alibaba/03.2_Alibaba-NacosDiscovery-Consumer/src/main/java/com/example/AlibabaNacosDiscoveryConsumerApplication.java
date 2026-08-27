package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AlibabaNacosDiscoveryConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosDiscoveryConsumerApplication.class, args);
    }

    @Bean
    // 接口调用需要微服务方式的：需要以负载均衡的方式调用(添加@LoadBalanced注解)；
    // 接口调用是直连方式的：则不需要负载均衡。直连的时候必须删除注解@LoadBalanced，否则访问失败
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}
