package com.example.bean.initializingBean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2023-03-09 23:00
 */
@Component
public class AppInitializingBean implements InitializingBean {

    /**
     * InitializingBean是Spring提供的拓展性接口，InitializingBean接口为bean提供了属性初始化后的处理方法，
     * 它只有一个afterPropertiesSet方法，凡是继承该接口的类，在bean的属性初始化后都会执行该方法。
     * 执行顺序优先级：构造方法、注解postConstruct，实现InitializingBean方法afterPropertiesSet，bean初始化init方法
     *              构造方法 > postConstruct > afterPropertiesSet > init11方法。
     *
     * 启动后可以在控制台看到当前类被初始化了两次。即构造方法、注解postConstruct、重写方法afterPropertiesSet 执行了两次
     * 原因在于注册该类的@Bean标注的方法test，new了当前对象，相当于再初始化了一次
     */

	public AppInitializingBean() {
		System.out.println("InitializingBean实现类的构造方法执行");
	}

	@PostConstruct
	public void methods() {
		System.out.println("postConstruct注解方法执行");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("接口initializingBean的实现方法执行");
	}

	public void init11() {
		System.out.println("我是init方法执行...");
	}

	@Bean(initMethod = "init11")
	public AppInitializingBean test() {
		return new AppInitializingBean();
	}

	// 普通方法
	public void init22() {
		System.out.println("我是普通方法执行...");
	}

	// 销毁
	@PreDestroy
	public void preDestory() {
		System.out.println("InitializingBean执行了销毁方法");
	}
}
