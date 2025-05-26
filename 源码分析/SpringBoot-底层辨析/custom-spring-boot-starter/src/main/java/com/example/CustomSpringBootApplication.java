package com.example;

import com.example.config.WebServerAutoConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-01 21:20:00
 * @apiNote TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan
// 将自动配置类注入到 Spring 容器中。
@Import(WebServerAutoConfiguration.class)
// TODO 但是这样就会有一个问题，如果后续自动配置类增多，那需要在这里引入的 @Import 就越多。
//      很明显这样的程序设计是不符合我们编程规范的。那有什么办法没有？答案是：AutoConfigurationImportSelector
public @interface CustomSpringBootApplication {}
