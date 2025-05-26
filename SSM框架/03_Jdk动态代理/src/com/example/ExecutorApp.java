package com.example;

import com.example.handler.MySellHandler;
import com.example.service.UsbSell;
import com.example.service.impl.UsbKingFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ExecutorApp {

	public static void main(String[] args) {
		// 创建代理对象，使用Proxy
		// 1、创建目标对象，多态对象
		UsbSell target = new UsbKingFactory();

		// 2、创建InvocationHandler对象
		InvocationHandler handler = new MySellHandler(target);

		// 3、创建代理对象
		//    代码内容其实也很好理解。正常加载一个类需要通过类加载器去加载吧，调用类方法还需要知道是哪个类吧
		//    参数一：target.getClass().getClassLoader() 获取到目标类的类加载器
		//    参数二：target.getClass().getInterfaces()  获取到目标类的接口类
		//           这里之所以传参是目标类的接口类，是因为接口类可以高扩展更多功能。
		//           而且我们是通过多态获取的对象，照样能获取到具体的目标类。
		//    参数三：handler 则是我们进行功能增强的实现类
		UsbSell proxy = (UsbSell) Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				handler
		);

		// 动态代理对象的本质是在运行时生成的一个实现了指定接口的代理类的实例。
		// 当你打印一个动态代理对象时，它的toString()方法默认会输出对象的类名和哈希码。
		// 这可能是不够直观的，因为它只显示了代理对象的类型，而不是其代理的接口或类。
		//
		// 为了更清晰地表达对象的类型，你可以使用getClass().getName()方法来获取代理对象的类名。
		// 这种方式更直接地显示了代理对象的类型，通常会显示出代理的接口或类名，这有助于更好地理解代理对象的含义。
		System.out.println("代理对象" + proxy.getClass().getName());

		// 4、通过代理执行方法
		float price = proxy.sell(1);
		System.out.println("通过动态代理对象，调用方法" + price);
	}
}
