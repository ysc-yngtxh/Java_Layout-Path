package com.example;

import com.example.config.UserServiceRibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
@EnableHystrix          // 启动Hystrix
@EnableDiscoveryClient  // 这个是实现注册中心(Eureka等一系列注册中心)的注解
@EnableFeignClients     // 启动Feign，这里的RestTemplate可以删除的
@RibbonClient(name = "consumer-ribbon", configuration = UserServiceRibbonConfig.class)
public class ConsumerApplication {

    @Bean
    @LoadBalanced   // 用来启用Ribbon的负载均衡处理
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
