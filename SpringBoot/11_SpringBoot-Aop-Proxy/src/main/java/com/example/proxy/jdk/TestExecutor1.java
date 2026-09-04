package com.example.proxy.jdk;

import com.example.service.ContainInterfaceService;
import com.example.service.impl.ContainInterfaceServiceImpl;
import java.lang.reflect.Proxy;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 22:00:00
 * @apiNote TODO 手动实现 JDK 动态代理
 */
public class TestExecutor1 {

	// 这里执行的是静态main()方法，没有注入Spring容器中，切面逻辑不会生效
	public static void main(String[] args) {
		ContainInterfaceService containInterfaceService = new ContainInterfaceServiceImpl();
		JdkProxy jdkProxy = new JdkProxy(containInterfaceService);

		ContainInterfaceService instance = (ContainInterfaceService) Proxy.newProxyInstance(
				containInterfaceService.getClass().getClassLoader(),
				containInterfaceService.getClass().getInterfaces(),
				jdkProxy
		);
		System.out.println("Jdk动态代理的执行结果：" + instance.definition());

		System.out.println(instance.getClass().getName());
	}
}
