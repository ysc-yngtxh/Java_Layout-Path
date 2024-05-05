package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author 游家纨绔
 */
// @EnableCircuitBreaker   // 这个注解是用来实现Hystrix的注解,用来开启熔断
// @EnableDiscoveryClient  // 这个是实现注册中心(Eureka等一系列注册中心)的注解
// @SpringBootApplication
@SpringCloudApplication  // 一个标准的服务是由以上三种注解的，但是这个注解涵盖以上三个注解
@EnableFeignClients      // 启动Feign，这里的RestTemplate可以删除的
public class ConsumerApplication {

    @Bean
    @LoadBalanced   // 用来做负载均衡的处理
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}

/*
  @EnableEurekaClient 中使用了 @EnableDiscoveryClient。因此，从使用角度来看两者已经没有什么区别了，官方建议使用EnableDiscoveryClient。
      区别：
        @EnableDiscoveryClient注解是基于spring-cloud-commons依赖，并且在classpath中实现。
        @EnableEurekaClient注解是基于spring-cloud-netflix依赖，只能为eureka作用。
        @EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心。

        从Spring Cloud 1.0.0.RC1版本开始，就已经不推荐使用EnableEurekaClient和EnableHystrix了。
        服务注册发现功能被抽象后放入spring-cloud-commons库，该库的EnableDiscoveryClient可以取代旧的EnableEurekaClient，
        使用注解EnableDiscoveryClient就能启用服务注册发现功能。 同理，EnableHystrix也被EnableCircuitBreaker取代了。
*/