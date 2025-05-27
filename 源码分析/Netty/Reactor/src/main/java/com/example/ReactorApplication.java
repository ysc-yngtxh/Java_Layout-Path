package com.example;

import com.example.single.Reactor;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactorApplication {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(ReactorApplication.class, args);
		// 启动Reactor服务器
		new Thread(new Reactor(8080)).start();
		System.out.println("Reactor Server started on port 8080");
	}

}
