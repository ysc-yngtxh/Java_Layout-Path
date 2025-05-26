package com.example.proxy;

import com.example.common.Invocation;
import com.example.common.RPC_URL;
import com.example.loadbalance.LoadBalance;
import com.example.protocol.HttpClient;
import com.example.register.MapRemoveRegister;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-08 上午9:40:00
 * @apiNote TODO
 */
public class ProxyFactory {

	// TODO 使用代理对象的同时，注册中心注册服务，并通过负载均衡决定请求服务器
	public static <T> T getProxyRegistrationCenter(Class<T> interfaceName) {
		Object proxyInstance = Proxy.newProxyInstance(
				interfaceName.getClassLoader()
				, new Class[]{interfaceName}
				, (Object proxy, Method method, Object[] arg) -> {

					// 定义mock
					String mock = System.getProperty("mock");
					if (mock != null && mock.startsWith("return:")) {
						return mock.replace("return:", "");
					}

					Invocation invocation = new Invocation(
							interfaceName.getSimpleName()
							, method.getName()
							, new Class[]{String.class}
							, arg
							, "1.0.0");

					HttpClient client = new HttpClient();

					// 服务发现
					List<RPC_URL> RPCUrls = MapRemoveRegister.get(interfaceName.getSimpleName());

					String res = null;
					List<RPC_URL> list = new ArrayList<>();
					// 模拟有三台服务器
					int max = 3;
					while (max > 0) {
						RPCUrls.remove(list);
						// 负载均衡
						RPC_URL RPCUrl = LoadBalance.random(RPCUrls);
						list.add(RPCUrl);

						// 服务调用
						try {
							res = client.sendRequest(RPCUrl.getHostName(), RPCUrl.getPort(), invocation);
						} catch (Exception e) {
							if (max-- != 0) {
								continue;
							}
							// error-callback = com.example.HelloServiceErrorCallback
							// 容错
							return "报错了";
						}
					}
					return res;
				});
		// 返回代理对象，并将代理对象强制转换为接口类型【Object --> T】
		return interfaceName.cast(proxyInstance);
	}

}
