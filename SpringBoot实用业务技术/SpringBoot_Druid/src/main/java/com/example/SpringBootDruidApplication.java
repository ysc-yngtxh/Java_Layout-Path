package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class SpringBootDruidApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDruidApplication.class, args);
	}

}
