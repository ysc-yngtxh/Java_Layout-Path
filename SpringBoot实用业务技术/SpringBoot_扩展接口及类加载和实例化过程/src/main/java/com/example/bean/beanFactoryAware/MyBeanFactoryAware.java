package com.example.bean.beanFactoryAware;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryAware implements BeanFactoryAware {
    @Getter
    private String brand;

    private BeanFactory beanFactory;

    public void setBrand(final String brand) {
        System.out.println("调用Car的setBrand属性");
        this.brand = brand;
    }

    // BeanFactoryAware接口是用来获取Spring框架自动初始化的Bean工厂
    @Override
    public void setBeanFactory(final BeanFactory beanFactory) throws BeansException {
        System.out.println("调用BeanFactoryAware的setBeanFactory方法--->" + beanFactory.containsBean("myBeanFactoryAware"));
        this.beanFactory = beanFactory;
    }
}