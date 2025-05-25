package com.example.framework;

import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-08 19:30:00
 * @apiNote TODO Bean定义
 */
@Data
public class BeanDefinition {

	// Bean的类对象
	private Class<?> clazz;

	// Bean的作用域
	private String scope;

}
