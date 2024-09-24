package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.mapper.springdb", "com.example.mapper.yun6"})
public class SpringBootReadWriteSplittingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReadWriteSplittingApplication.class, args);
	}

}
