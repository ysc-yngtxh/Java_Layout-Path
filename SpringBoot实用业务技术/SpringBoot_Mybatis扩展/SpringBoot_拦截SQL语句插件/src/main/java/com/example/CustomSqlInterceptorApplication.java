package com.example;

import com.example.annotation.EnableSqlInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 使用自定义的注解，表示开启 mybatis 拦截器配置
@EnableSqlInterceptor
@MapperScan(basePackages = "com.example.mapper")
public class CustomSqlInterceptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomSqlInterceptorApplication.class, args);
	}
}
