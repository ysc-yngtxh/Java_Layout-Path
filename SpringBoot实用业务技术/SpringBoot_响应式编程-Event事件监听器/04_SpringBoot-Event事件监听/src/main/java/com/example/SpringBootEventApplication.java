package com.example;

import com.example.event.CodeLogicEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

// 启动类增加此注解，开启异步支持。
@EnableAsync
@SpringBootApplication
public class SpringBootEventApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringBootEventApplication.class);
		// 添加自定义监听器
		springApplication.addListeners(new CodeLogicEventListener());
		springApplication.run(args);
	}

}
