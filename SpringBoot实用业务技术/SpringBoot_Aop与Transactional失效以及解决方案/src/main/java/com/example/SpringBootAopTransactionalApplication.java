package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// 暴露代理，方便获取当前代理对象
@EnableAspectJAutoProxy(exposeProxy=true)
@SpringBootApplication
public class SpringBootAopTransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAopTransactionalApplication.class, args);
    }

}
