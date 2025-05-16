package com.example.bean.beanPostProcessor;

import com.example.bean.initializingBean.AppInitializingBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-27 14:00
 * @apiNote TODO BeanPostProcessor —— 直译：对象后处理器
 *               指的是“对象创建后处理器”，在对象创建(属性注入)之后，对对象进行修改。
 */
@Component
public class MyBeanPostProcess implements BeanPostProcessor {

	// 实现BeanPostProcessor接口重写对指定Bean初始化的前置方法和后置方法
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// 请注意：业务逻辑一定要书写在判断操作的Bean中，不要直接书写逻辑，否则面向的是Spring容器中所有的Bean
		// 在指定 bean 初始化之前执行的代码
		if (bean instanceof AppInitializingBean) {
			System.out.println("BeanPostProcessor执行了指定Bean初始化的前置方法");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// 在指定 bean 初始化完之后执行的代码
		if (beanName.equalsIgnoreCase("appI18nConfig")) {
			System.out.println("BeanPostProcessor执行了指定Bean初始化的后置方法");
		}
		return bean;
	}
}
