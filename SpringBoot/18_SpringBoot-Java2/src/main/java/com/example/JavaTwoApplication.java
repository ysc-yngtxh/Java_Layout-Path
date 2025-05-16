package com.example;

import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
public class JavaTwoApplication implements CommandLineRunner {

	@Autowired
	private StudentService studentService;

	public static void main(String[] args) {
		// springboot启动程序，会初始化spring容器
		SpringApplication.run(JavaTwoApplication.class, args);
	}

	// 重写 CommandLineRunner 类中的run方法
	@Override
	public void run(String... args) throws Exception {

		// 调用业务方法
		String sayHello = studentService.sayHello("World");

		System.out.println(sayHello);
	}

	/* 这里来说明一下，为什么SpringBoot框架Java工程写法模式里第一种方法不需要注入引用，而第二种方法却需要？
	 *   因为第一种是在main方法里执行，main方法是一个静态方法，但是你用注解@Autowired的引用注入不是一个静态对象，
	 *   所以第一种方法不加注解注入的。而且，你在第一种方法里可以从spring容器里获取bean对象。
	 */
}
