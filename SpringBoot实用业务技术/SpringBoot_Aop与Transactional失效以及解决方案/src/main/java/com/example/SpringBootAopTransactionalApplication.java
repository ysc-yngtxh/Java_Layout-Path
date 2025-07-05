package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

// 暴露代理，方便获取当前代理对象。
@EnableAspectJAutoProxy(exposeProxy = true) // 开启spring注解aop配置的支持
@EnableAsync // 开启异步处理
@SpringBootApplication
@MapperScan("com.example.transactional.mapper")
public class SpringBootAopTransactionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAopTransactionalApplication.class, args);
	}

}
