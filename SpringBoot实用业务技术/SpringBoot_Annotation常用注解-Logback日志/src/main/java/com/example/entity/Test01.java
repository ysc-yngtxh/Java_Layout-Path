package com.example.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Test01是一个拥有1个静态变量的类
 */
@Component
public class Test01 {

	// 该静态变量的属性值需要通过Spring容器赋值，值(hello)定义在application.properties中。
	// 注意：@Value注解不可以给静态变量注入属性值 (否则获取的注入结果为null) ！
	//      因此HELLO的属性值注入要在setter方法上加上@Value注解，
	public static String HELLO;

	public static String WORLD;

	@Value("${spring.test.hello}")
	public void setHELLO(String hello) {
		HELLO = hello;
	}

	@Value("${spring.test.world}")
	public void setWORLD(String world) {
		WORLD = world;
	}
}
