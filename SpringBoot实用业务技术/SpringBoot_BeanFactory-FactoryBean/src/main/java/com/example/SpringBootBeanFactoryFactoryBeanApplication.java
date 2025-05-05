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
        // 直接从Bean工厂中获取Bean
        StableServiceImpl testService = applicationContext.getBean("stableServiceImpl", StableServiceImpl.class);
        testService.doService();

        // 获取Bean对象ServiceFactory(实现接口FactoryBean的类)，但是这里获取的是FactoryBean的装饰对象
        Object serviceFactory = applicationContext.getBean("serviceFactory");
        System.out.println("123 -- " + serviceFactory);

        // 获取ServiceFactory类的原生对象
        Object serviceFactory2 = applicationContext.getBean("&serviceFactory");
        System.out.println("456 -- " + serviceFactory2);
    }
    /**
     * 区别
     * BeanFactory: 负责生产和管理Bean的一个工厂接口，提供一个Spring Ioc容器规范,
     * FactoryBean: 一种Bean创建的一种方式，对Bean的一种扩展。对于复杂的Bean对象初始化创建使用其可封装对象的创建细节。
     */
}
