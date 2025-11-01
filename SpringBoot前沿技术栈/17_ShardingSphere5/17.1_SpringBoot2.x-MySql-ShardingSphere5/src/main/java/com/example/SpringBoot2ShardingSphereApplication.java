package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")
public class SpringBoot2ShardingSphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2ShardingSphereApplication.class, args);
	}

}
