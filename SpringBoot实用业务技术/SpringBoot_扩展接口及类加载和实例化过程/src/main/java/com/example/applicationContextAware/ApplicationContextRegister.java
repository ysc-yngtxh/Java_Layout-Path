package com.example.applicationContextAware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextRegister implements ApplicationContextAware {
    private static ApplicationContext APPLICATION_CONTEXT;

    /*
      简单地说，ApplicationContextAware接口是用来获取框架自动初始化的ioc容器对象的。
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        APPLICATION_CONTEXT = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    // 通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    // 通过name，以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}