package com.example.config;

import com.example.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-22 00:30:00
 * @apiNote TODO
 */
@Configuration
public class AppConfig {

	@Bean(value = "myUser")
	public User user() {
		return new User();
	}
}
