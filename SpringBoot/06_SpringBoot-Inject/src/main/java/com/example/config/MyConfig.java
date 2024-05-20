package com.example.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-12 12:11
 * @apiNote TODO
 */
@Configuration
public class MyConfig {

    @Bean(autowire = Autowire.BY_TYPE)
    public UserService userService() {
        return new UserServiceImpl();
    }
}
