package com.example;

import com.example.controller.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 游家纨绔
 * @Description TODO Springboot项目启动入口类
 */
@SpringBootApplication // Springboot核心注解，主要用于开启Spring自动配置
public class FirstApplication {

	public static void main(String[] args) {
		// TODO ⚠️
		//  在同一个应用程序中同时使用 AnnotationConfigApplicationContext 和 SpringApplication.run 可能会导致冲突.
		//  因为它们都是用来初始化 Spring 应用上下文的方法。通常情况下，你只需要使用其中一种方法。
		//  如果你需要自定义 Spring 上下文的初始化过程，你可以使用 AnnotationConfigApplicationContext；
		//  而如果你想要使用 SpringBoot 的自动配置和默认行为，你可以使用 SpringApplication.run 方式。
		ConfigurableApplicationContext applicationContext = SpringApplication.run(FirstApplication.class, args);

		System.out.println(applicationContext.getBean("myController", MyController.class));
	}
}
