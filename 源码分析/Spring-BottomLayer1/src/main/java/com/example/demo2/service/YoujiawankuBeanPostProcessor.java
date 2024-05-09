package com.example.demo2.service;

import com.example.spring.interfaces.BeanPostProcessor;
import com.example.spring.annatation.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-09 06:26
 * @apiNote TODO
 */
@Component("youjiawankuBeanPostProcessor")
public class YoujiawankuBeanPostProcessor implements BeanPostProcessor {

    // Bean初始化前后的后置处理
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println(beanName + "初始化前～");
        // 比如在这里单独对 UserService1 的 Bean 做处理
        if (bean instanceof UserService2) {
            UserService2 userService = (UserService2) bean;
            userService.setName("游家纨绔");
        }
        return bean;
    }

    // Bean初始化后的后置处理
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println(beanName + "初始化后～");
        return bean;
    }
}