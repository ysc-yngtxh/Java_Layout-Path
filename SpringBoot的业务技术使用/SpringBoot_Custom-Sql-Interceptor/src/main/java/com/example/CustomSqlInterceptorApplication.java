package com.example;

import com.example.annotation.EnableSqlInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.dao")
@EnableSqlInterceptor
public class CustomSqlInterceptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomSqlInterceptorApplication.class, args);
	}

}
