package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class JpaSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaSpringBootApplication.class, args);
	}

}
