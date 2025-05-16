package com.example;

import com.example.handler.MyInvocationHandler;
import com.example.service.SomeService;
import com.example.service.SomeServiceImpl;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MyApp {

	public static void main(String[] args) {
		// 使用jdk的Proxy创建对象
		// 创建目标对象
		SomeService target = new SomeServiceImpl();

		// 创建InvocationHandler对象
		InvocationHandler handler = new MyInvocationHandler(target);

		// 使用Proxy创建代理
		//    这段代码内容其实也很好理解。正常加载一个类需要通过类加载器去加载吧，调用类方法还需要知道是哪个类吧
		//    参数一：target.getClass().getClassLoader() 获取到目标类的类加载器
		//    参数二：target.getClass().getInterfaces()  获取到目标类的接口类
		//           这里之所以传参是目标类的接口类，是因为接口类可以高扩展更多功能。
		//           而且我们是通过多态获取的对象，照样能获取到具体的目标类。
		//    参数三：handler 则是我们进行功能增强的实现类
		SomeService proxy = (SomeService) Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				handler
		                                                        );

		// 通过代理执行方法，会调用handler中的invoke()
		proxy.doSome();
		System.out.println("===========================");
		proxy.doOther();
	}

}
