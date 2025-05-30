package com.yupi.yurpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂（用于创建代理对象）
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @learn <a href="https://codefather.cn">编程宝典</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public class ServiceProxyFactory {

	/**
	 * 根据服务类获取代理对象
	 *
	 * @param serviceClass
	 * @param <T>
	 * @return
	 */
	public static <T> T getProxy(Class<T> serviceClass) {
		return (T) Proxy.newProxyInstance(
				serviceClass.getClassLoader(),
				new Class[]{serviceClass},
				new ServiceProxy());
	}
}
