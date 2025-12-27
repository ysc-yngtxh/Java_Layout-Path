package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
public class SpringBootWebFluxAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebFluxAnnotationApplication.class, args);
	}

}
