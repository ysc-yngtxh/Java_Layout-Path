package com.example;

import com.example.proxy.ProxyFactory;
import com.example.service.HelloService;

public class ConsumerApplication2 {

	public static void main(String[] args) {
		// TODO 先开启Provider，再启动Consumer

		// 通过代理对象调用目标对象的方法
		HelloService helloService = ProxyFactory.getProxy(HelloService.class);
		String sayHello = helloService.sayHello("World");
		System.out.println(sayHello);
	}

}
