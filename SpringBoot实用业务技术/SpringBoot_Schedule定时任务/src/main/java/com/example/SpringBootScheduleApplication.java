package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// 开启定时任务
@EnableScheduling
@SpringBootApplication
public class SpringBootScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootScheduleApplication.class, args);
	}
}
