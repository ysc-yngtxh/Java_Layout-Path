package com.example;

import com.example.proxy.ProxyFactory;
import com.example.service.HelloService;

public class ConsumerApplication4 {

	public static void main(String[] args) {
		// TODO 先开启Provider，再启动Consumer

		// 通过代理对象调用目标对象的方法，并且使用注册中注册过的服务，并通过负载均衡分发服务器
		HelloService helloService = ProxyFactory.getProxyRegistrationCenter(HelloService.class);
		String sayHello = helloService.sayHello("张三");
		System.out.println(sayHello);
	}

}
