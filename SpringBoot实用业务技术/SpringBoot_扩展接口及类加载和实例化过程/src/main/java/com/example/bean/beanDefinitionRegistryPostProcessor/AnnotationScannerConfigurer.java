package com.example.bean.beanDefinitionRegistryPostProcessor;

import com.example.annotation.AutoDiscoverClass;
import com.example.executor.Category;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-27 14:17
 * @apiNote TODO 动态注册Bean到Spring容器
 */
@Component
public class AnnotationScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

	// TODO 1、Spring中的每个bean的创建是依赖一个对应的BeanDefinition实例，该实例保存了bean对象创建必要的信息，
	//         比如bean的class类型，是否是抽象类、属性信息等。
	//      2、BeanDefinitionRegistry是一个接口，bean定义信息的注册中心，用于注册、删除、管理BeanDefinition。
	//         如果外部想要添加Bean的定义信息，创建新的Bean, 怎么办？ 当然是可以通过Spring提供的扩展接口BeanDefinitionRegistryPostProcessor实现，
	//         比较典型的一个案例是mybatis-spring，就是通过实现该接口，添加mybatis相关的bean。
	//      3、所以，BeanDefinitionRegistryPostProcessor就是bean定义注册中心的后置处理器，
	//         允许我们修改拓展bean定义信息的注册中心，在所有bean定义信息将要被加载，bean实例还未创建的时候执行。

	// 该方法的实现中，主要用来对bean定义做一些改变。
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
		// 获取指定类的Bean定义
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Category.class).getBeanDefinition();
		beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
		MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
		propertyValues.addPropertyValue("id", 1);
		propertyValues.addPropertyValue("categoryName", "小曹哇小曹");
		propertyValues.addPropertyValue("categoryNameEn", "Cao");
		propertyValues.addPropertyValue("deleteFlag", 0);
		propertyValues.addPropertyValue("remark", "这是个坏女娃！");

		// 如果不想定义属性，可以直接创建一个bean(Category.class)的定义类的对象RootBeanDefinition
		// RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Category.class);

		// 将Bean 的定义注册到Spring环境，并定义该Bean的名称为"testService"
		beanDefinitionRegistry.registerBeanDefinition("testService", beanDefinition);
	}

	// 这个方法通常用于处理已经加载到容器中的Bean
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
		// 获取每一个含有自定义注解@AutoDiscoverClass的Bean。bean的名字为key, bean的实例为value
		Map<String, Object> beanMap =
				configurableListableBeanFactory.getBeansWithAnnotation(AutoDiscoverClass.class);
		System.err.println(beanMap.get("testService"));
	}
}