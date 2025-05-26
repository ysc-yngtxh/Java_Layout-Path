package com.example.proxy;

import com.example.common.Invocation;
import com.example.protocol.HttpClient;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-08 上午9:40:00
 * @apiNote TODO
 */
public class ProxyFactory {

	// 使用代理对象发送请求，这里需要自己写死请求路径
	public static <T> T getProxy(Class<T> interfaceName) {
		Object proxyInstance = Proxy.newProxyInstance(
				interfaceName.getClassLoader()
				, new Class[]{interfaceName}
				, (Object proxy, Method method, Object[] arg) -> {
					Invocation invocation = new Invocation(
							interfaceName.getSimpleName()
							, method.getName()
							, new Class[]{String.class}
							, arg
							, "1.0.0"
					);

					HttpClient client = new HttpClient();
					String res = client.sendRequest("localhost", 8080, invocation);
					return res;
				});
		// 返回代理对象，并将代理对象强制转换为接口类型【Object --> T】
		return interfaceName.cast(proxyInstance);
	}

}
