package com.example.bean.beanNameAware;

import org.springframework.beans.factory.BeanNameAware;

public class NormalBeanA implements BeanNameAware {

	public NormalBeanA() {
		System.out.println("NormalBean constructor");
	}

	// Spring创建Bean之前会先实例化Bean定义[BeanDefinition]，该实例保存了Bean对象创建必要的信息
	// 因此在初始化该Bean之前拿到spring容器BeanDefinition中注册的beanName，来自行修改这个beanName的值。
	@Override
	public void setBeanName(String name) {
		System.out.println("[BeanNameAware] " + name);
	}
}   
