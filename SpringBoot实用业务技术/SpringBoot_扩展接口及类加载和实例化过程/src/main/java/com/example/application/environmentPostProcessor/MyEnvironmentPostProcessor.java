package com.example.application.environmentPostProcessor;

import java.util.List;
import org.checkerframework.checker.units.qual.C;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-21 11:00
 * @apiNote TODO 在SpringBoot启动的初期阶段，对应用程序的环境属性进行修改、添加、删除
 */
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        ClassPathResource resource = new ClassPathResource("page.yml");
        if (!resource.exists()) {
            throw new IllegalStateException("Resource not found: " + "page.yml");
        }

        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
        try {
            List<PropertySource<?>> sources = loader.load("custom-resource", resource);
            if (!sources.isEmpty()) {
                // 添加到属性源的最前面，确保优先级最高
                environment.getPropertySources().addFirst(sources.getFirst());
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load yaml configuration from " + "page.yml", ex);
        }

        // EnvironmentPostProcessor 需要在应用上下文创建之前就被加载（因为它负责准备环境变量）。
        // 此时 Spring 的依赖注入和组件扫描机制尚未启动，因此必须通过 SPI 显式注册。
        //    Spring SPI扩展，在spring.factories中加入：
        //    org.springframework.boot.env.EnvironmentPostProcessor=\
        //    com.example.application.environmentPostProcessor.MyEnvironmentPostProcessor
    }
}
