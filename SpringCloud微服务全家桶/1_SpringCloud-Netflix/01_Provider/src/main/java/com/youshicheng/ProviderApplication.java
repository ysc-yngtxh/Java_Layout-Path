package com.youshicheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 游家纨绔
 */
// 这个注解是用来响应注册中心Eureka
// @EnableDiscoveryClient  如果注册中心不是Eureka，是其他的就用这个注解响应注册中心。当然Eureka也能用
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.youshicheng.mapper")
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }



}
