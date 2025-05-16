package com.example;

import com.example.service.HelloService;
import com.example.service.impl.HelloServiceImpl;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestApp {

	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// HelloService service = new HelloServiceImpl();
		// service.sayHello("张三");

		// 使用多态机制，new出类对象
		HelloService target = new HelloServiceImpl();
		// 使用反射机制获取到目标方法的代理类Method
		Method method = HelloService.class.getMethod("sayHello", String.class);

		/* invoke是Method类中的一个方法，表示执行方法的调用
		 * 参数：
		 *     参数1、Object，表示对象的，要执行这个对象的方法
		 *     参数2、Object...args，方法执行时的参数值
		 * 返回值：
		 *     Object：方法执行后的返回值
		 */
		// 代码意思是：执行target对象的 sayHello() 方法，参数为'李四'
		Object ret = method.invoke(target, "李四");
	}
}
