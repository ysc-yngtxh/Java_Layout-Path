package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 游家纨绔
 */
// Springboot项目启动入口类
@SpringBootApplication   // Springboot核心注解，主要用于开启Spring自动配置
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
