package com.example;

import com.example.modul.Hello;
import com.example.modul.World;
import com.example.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Map;
import java.util.Set;

// 开启AOP
// proxyTargetClass：默认值为false，表示使用JDK动态代理。将其设置为true，表示不论有没有接口都使用CGLIB动态代理。
// exposeProxy：默认值为false，表示不暴露代理对象。将其设置为true，表示暴露代理对象。即可以对当前类中调用方法进行AOP。
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@SpringBootApplication
public class SpringBottomLayer2Application {

    public static void main(String[] args) {

        // 创建一个ApplicationContext容器，用于管理Spring bean的生命周期。
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringBottomLayer2Application.class);

        // 在同一个应用程序中同时使用 AnnotationConfigApplicationContext 和 SpringApplication.run 可能会导致冲突.
        // 因为它们都是用来初始化 Spring 应用上下文的方法。通常情况下，你只需要使用其中一种方法。
        // 如果你需要自定义 Spring 上下文的初始化过程，你可以使用 AnnotationConfigApplicationContext；
        // 而如果你想要使用 Spring Boot 的自动配置和默认行为，你可以使用 SpringApplication.run。
        SpringApplication.run(SpringBottomLayer2Application.class, args); // 注意：

        // registerBean方法允许注册一个bean定义，可以配置bean的类、构造函数参数、作用域和其他属性。
        // 使用registerBean方法注册的bean可以在ApplicationContext容器中注入其他依赖、使用Autowiring等Spring功能。
        applicationContext.registerBean("hello", Hello.class);

        // registerSingleton方法用于注册一个单例实例的bean，即直接将一个已经实例化的对象注册到Spring容器中。
        // 这个对象将被视为单例，即每次从容器中获取该bean时都返回同一个实例。
        // 使用registerSingleton方法注册的bean不能配置构造函数参数、作用域等属性，只能简单地注册一个实例。
        applicationContext.getBeanFactory().registerSingleton("world", new World());

        // Spring 的 Aop 底层原理
        ProductService productService = applicationContext.getBean("productService", ProductService.class);
        productService.test();
        
        // 推断构造方法 -> 普通对象 -> 依赖注入 -> 初始化 afterPropertiesSet -> 初始化后Aop -> 代理对象 -> 放入 Map<beanName, bean对象>
    }

}
