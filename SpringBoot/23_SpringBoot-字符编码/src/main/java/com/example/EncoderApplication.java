package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.example.servlet")
public class EncoderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncoderApplication.class, args);
	}

}
