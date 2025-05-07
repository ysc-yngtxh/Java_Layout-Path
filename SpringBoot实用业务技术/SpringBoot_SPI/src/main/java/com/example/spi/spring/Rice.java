package com.example.spi.spring;

import com.example.dependency.EnableCustomDependency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(Rice.class);

    @Override
    public void sayHello() {
        log.debug("Hello, I am Optimus Prime.");
        System.out.println("Hello, I am Optimus Prime.");
    }

    @Bean
    public CustomTemplate customTemplate() {
        return new CustomTemplate();
    }

    class CustomTemplate {}
}
