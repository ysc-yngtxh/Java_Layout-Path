package com.example;

import com.example.service.TestService;
import com.example.service.impl.StableServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootBeanFactoryFactoryBeanApplication {

    public static void main(String[] args) {
        // BeanFactory: 一个 Bean 工厂，负责 Bean 的生产和管理
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootBeanFactoryFactoryBeanApplication.class, args);
        StableServiceImpl testService = (StableServiceImpl) applicationContext.getBean("stableServiceImpl");
        testService.doService();
    }

}
