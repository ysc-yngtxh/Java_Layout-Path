package com.example;

import com.example.entity.Brand;
import com.example.entity.Test01;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@EnableAsync // 开启异步支持
@RestController
@SpringBootApplication
@MapperScan("com.example.dao")
public class SpringBootAnnotationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootAnnotationApplication.class, args);

        System.out.println("当前环境语言：" +
                (run.getEnvironment().getProperty("user.language").equalsIgnoreCase("zh") ? "中文" : "非中文"));

        System.out.println(
                Arrays.stream(run.getBeanDefinitionNames()).toList().contains("conditionalConfig")
                ? "ConditionalConfig类Bean已注入" : "ConditionalConfig类Bean未注入");

        System.out.println(
                Arrays.stream(run.getBeanDefinitionNames()).toList().contains("brandExpression")
                ? "brandExpression类Bean已注入" : "brandExpression类Bean未注入");

        Map<String, Brand> beansOfType = run.getBeansOfType(Brand.class);
        beansOfType.forEach( (key, value) -> {
            System.out.println(value);
        });

        System.out.println(Test01.HELLO + " " + Test01.WORLD);
    }
}
