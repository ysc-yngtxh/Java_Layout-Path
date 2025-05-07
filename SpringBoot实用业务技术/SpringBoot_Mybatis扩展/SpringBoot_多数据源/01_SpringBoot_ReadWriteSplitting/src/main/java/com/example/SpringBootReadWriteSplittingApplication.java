package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.mapper.business", "com.example.mapper.business2"})
public class SpringBootReadWriteSplittingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReadWriteSplittingApplication.class, args);
	}

}
