package com.example.framework.interfaces;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-09 06:06
 * @apiNote TODO Bean初始化的后置处理器
 */
public interface BeanPostProcessor {

    // Bean初始化前后的后置处理
    Object postProcessBeforeInitialization(Object bean, String beanName);

    // Bean初始化后的后置处理
    Object postProcessAfterInitialization(Object bean, String beanName);
}
