package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SentinelDataSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentinelDataSourceApplication.class, args);
	}

	@RequestMapping("/test")
	public String test() {
		return "你好哦！！！";
	}
}
