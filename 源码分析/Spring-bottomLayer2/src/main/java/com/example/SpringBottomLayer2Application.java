package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringBottomLayer2Application {

    public static void main(String[] args) {

        // 创建一个ApplicationContext容器，用于管理Spring bean的生命周期。
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringBottomLayer2Application.class);

        // registerBean方法允许注册一个bean定义，可以配置bean的类、构造函数参数、作用域和其他属性。
        // 使用registerBean方法注册的bean可以在ApplicationContext容器中注入其他依赖、使用Autowiring等Spring功能。
        applicationContext.registerBean("hello", Hello.class);

        // registerSingleton方法用于注册一个单例实例的bean，即直接将一个已经实例化的对象注册到Spring容器中。
        // 这个对象将被视为单例，即每次从容器中获取该bean时都返回同一个实例。
        // 使用registerSingleton方法注册的bean不能配置构造函数参数、作用域等属性，只能简单地注册一个实例。
        applicationContext.getBeanFactory().registerSingleton("hello", new Hello());
        SpringApplication.run(SpringBottomLayer2Application.class, args);
    }

}
