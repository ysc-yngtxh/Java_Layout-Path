package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@MapperScan("com.example.mapper")
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true) // 应该是启用接口方法的权限注解
public class AuthorizationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AuthorizationApplication.class, args);
        System.out.println(applicationContext);
    }
}
