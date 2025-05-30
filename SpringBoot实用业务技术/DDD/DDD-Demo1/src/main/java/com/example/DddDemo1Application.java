package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.ddd.infrastucture.dao", "com.example.mvc.dao"})
public class DddDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(DddDemo1Application.class, args);
	}

}
