package com.example.spi.spring;

import com.example.dependency.EnableCustomDependency;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:00
 * @apiNote TODO
 */
@Configuration
@ConditionalOnClass({EnableCustomDependency.class})
public class Rice implements Food {

    @Override
    public void sayHello() {
        System.out.println("Hello, I am Rice.");
    }

    @Bean
    public CustomTemplate customTemplate() {
        return new CustomTemplate();
    }

    class CustomTemplate {}
}
