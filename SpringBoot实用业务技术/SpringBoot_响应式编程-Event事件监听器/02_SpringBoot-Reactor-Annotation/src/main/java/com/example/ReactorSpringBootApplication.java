package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactorSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactorSpringBootApplication.class, args);
	}

	/** 注解式驱动编程模型
	 *
	 * Spring Webflux 请求和响应不再是ServletRequest和ServletResponse，而是ServerRequest和ServerResponse。
	 *
	 * Flux 用于表示多个数据的流，而 Mono 用于表示单个数据。
	 */
}
