package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true) //应该是启用接口方法的权限注解
public class PreAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreAuthorizationApplication.class, args);
	}

}
