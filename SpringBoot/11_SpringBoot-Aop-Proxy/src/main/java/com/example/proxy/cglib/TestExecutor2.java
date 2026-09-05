package com.example.proxy.cglib;

import com.example.service.impl.DirectServiceImpl;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 22:10:00
 * @apiNote TODO 手动实现 Cglib 动态代理
 */
public class TestExecutor2 {

	// Cglib 动态代理的实现步骤：
    //    1. 创建一个被代理类的实例对象
    //    2. 创建一个 CglibInterceptor 拦截器对象，并将被代理类的实例对象传入
    //    3. 创建一个 Enhancer 对象，并设置被代理类的父类
    //    4. 设置 Enhancer 对象的回调函数为 CglibInterceptor �拦截器对象
    //    5. 调用 Enhancer 对象的 create()  方法创建代理对象
	public static void main(String[] args) {
		DirectServiceImpl directService = new DirectServiceImpl();
		CgLibInterceptor cglibInterceptor = new CgLibInterceptor(directService);

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(directService.getClass());
		enhancer.setCallback(cglibInterceptor);
		DirectServiceImpl service = (DirectServiceImpl) enhancer.create();
		service.direct();

		System.out.println(service.getClass().getName());
	}
}
