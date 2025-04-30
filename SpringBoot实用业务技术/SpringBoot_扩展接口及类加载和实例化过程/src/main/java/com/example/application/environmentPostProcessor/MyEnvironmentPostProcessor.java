package com.example.application.environmentPostProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-21 上午11:04
 * @apiNote TODO 在SpringBoot启动的初期阶段，对应用程序的环境属性进行修改、添加、删除
 */
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
        ClassPathResource path = new ClassPathResource("page.yml");
        PropertySource<?> propertySource;
        try {
            // 加载application.yml文件，并添加到Spring容器中
            propertySource = sourceLoader.load("custom-resource", path).get(0);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load yaml configuration from " + path, ex);
        }
        environment.getPropertySources().addLast(propertySource);

        // 因为这时候Spring容器还没被初始化，所以想要自己的扩展的生效，需要使用以下方式：
        //    Spring SPI扩展，在spring.factories中加入：
        //    org.springframework.boot.env.EnvironmentPostProcessor=\
        //    com.example.application.environmentPostProcessor.MyEnvironmentPostProcessor
    }
}
