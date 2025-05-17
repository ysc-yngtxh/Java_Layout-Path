package com.example.v2.service;

import com.example.framework.annotation.Autowired;
import com.example.framework.annotation.Component;
import com.example.framework.annotation.Scope;
import com.example.framework.interfaces.BeanNameAware;
import com.example.framework.interfaces.InitializingBean;
import lombok.Setter;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-08 20:20:00
 * @apiNote TODO 模拟引用注入
 */
@Component("userServiceImpl")
@Scope("protoType")
public class UserServiceImpl implements UserService, BeanNameAware, InitializingBean {

	@Autowired
	private OrderService orderService;

	@Setter
	private String name;

	// UserService接口的实现方法
	@Override
	public void test() {
		System.out.println("orderService = " + orderService);
		// 比如说我们希望在当前类中获取到 Bean 的名称并打印。那么可以通过 Aware 回调实现
		System.out.println("beanName = " + name);
	}

	// BeanNameAware接口的实现方法
	@Override
	public void setBeanName(String beanName) {
		this.name = beanName;
	}

	// InitializingBean接口的实现方法
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("初始化～");
	}

}
