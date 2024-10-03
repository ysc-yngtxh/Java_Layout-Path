package com.example;

import com.example.config.ContextProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootMetadataApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootMetadataApplication.class, args);
        ContextProperties bean = applicationContext.getBean(ContextProperties.class);
        System.out.println(bean);
    }

}
