package com.example.proxy;

import com.example.common.Invocation;
import com.example.common.RPC_URL;
import com.example.loadbalance.LoadBalance;
import com.example.protocol.HttpClient;
import com.example.register.MapRemoveRegister;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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

					Invocation invocation = new Invocation(
							interfaceName.getSimpleName()
							, method.getName()
							, new Class[]{String.class}
							, arg
							, "1.0.0"
					);

					HttpClient client = new HttpClient();

					// 服务发现
					List<RPC_URL> RPCUrls = MapRemoveRegister.get(interfaceName.getSimpleName());

					// 负载均衡
					RPC_URL RPCUrl = LoadBalance.random(RPCUrls);

					// 服务调用
					String res = client.sendRequest(RPCUrl.getHostName(), RPCUrl.getPort(), invocation);
					return res;
				});
		// 返回代理对象，并将代理对象强制转换为接口类型【Object --> T】
		return interfaceName.cast(proxyInstance);
	}

}
