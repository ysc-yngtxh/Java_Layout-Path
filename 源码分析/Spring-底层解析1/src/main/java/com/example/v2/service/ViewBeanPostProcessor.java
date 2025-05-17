package com.example.v2.service;

import com.example.framework.annotation.Component;
import com.example.framework.interfaces.BeanPostProcessor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-09 06:30:00
 * @apiNote TODO
 */
@Component("viewBeanPostProcessor")
public class ViewBeanPostProcessor implements BeanPostProcessor {

	// Bean初始化前后的后置处理
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		System.out.println(beanName + "初始化前～");
		// 比如在这里单独对 UserServiceImpl 的 Bean 做处理
		if (bean instanceof UserServiceImpl) {
			UserServiceImpl userServiceImpl = (UserServiceImpl) bean;
			userServiceImpl.setName("游家纨绔");
		}
		return bean;
	}

	// Bean初始化后的后置处理
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		System.out.println(beanName + "初始化后～");
		if (beanName.equals("userServiceImpl")) {
			// 用以模拟 Aop 操作
			UserService proxyInstance = (UserService) Proxy.newProxyInstance(
					bean.getClass().getClassLoader()
					, bean.getClass().getInterfaces()
					, new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							System.out.println("代理逻辑");
							return method.invoke(bean, args);
						}
					}
			                                                                );
			return proxyInstance;
		}
		return bean;
	}

}
