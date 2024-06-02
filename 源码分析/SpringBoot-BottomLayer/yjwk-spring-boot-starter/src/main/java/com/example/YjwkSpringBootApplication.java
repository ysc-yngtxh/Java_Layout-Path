package com.example;

import com.example.config.WebServerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-01 21:19
 * @apiNote TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan
@Import(WebServerAutoConfiguration.class) // 将自动配置类注入到 Spring 容器中
public @interface YjwkSpringBootApplication {

}
