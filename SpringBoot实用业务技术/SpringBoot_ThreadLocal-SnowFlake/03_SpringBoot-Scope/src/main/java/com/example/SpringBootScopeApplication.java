package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootScopeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootScopeApplication.class, args);
	}


	// ThreadLocal应用场景：比如说在同一个线程中，a调用b，b调用c。那么如果有共享数据只能通过参数传递，非常麻烦。
	// 但如果使用ThreadLocal，b和c就可以直接访问ThreadLocal中的数据，不需要通过参数传递。
	// 实际场景：可以在过滤器或者拦截器中，存放一个用户Id
}
