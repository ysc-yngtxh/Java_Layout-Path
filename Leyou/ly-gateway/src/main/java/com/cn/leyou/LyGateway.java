package com.cn.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class LyGateway {
    public static void main(String[] args) {
        SpringApplication.run(LyGateway.class,args);
    }
}
