package com.example.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-12 12:00:00
 * @apiNote TODO
 */
@Configuration
public class MyConfig {

	// 设置 Bean 的自动注入方式为按类型注入。
	// 即对应的 UserService 类型中引用注入是依靠 setter 方式自动注入的。
	// 但是这种方式太强大了，会将所有的 setter 方法都进行引用注入，造成不可控，因此是不推荐使用的。
	@Bean(autowire = Autowire.BY_TYPE)
	public UserService userService() {
		return new UserServiceImpl();
	}
}
