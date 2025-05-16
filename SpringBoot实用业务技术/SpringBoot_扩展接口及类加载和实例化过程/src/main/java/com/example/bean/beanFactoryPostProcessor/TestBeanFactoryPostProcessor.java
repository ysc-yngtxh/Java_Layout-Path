package com.example.bean.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.lang.NonNull;

public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	// 这个接口是beanFactory的扩展接口，调用时机在Spring在读取beanDefinition信息之后，实例化bean之前。
	// 在这个时机，用户可以通过实现这个扩展接口来自行处理一些东西，比如修改已经注册的beanDefinition的元信息。
	@Override
	public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("[BeanFactoryPostProcessor]");
	}
}   
