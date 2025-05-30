package com.example.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器链，存放所有拦截器，和对代理对象进行循环代理
 */
public class InterceptorChain {

	private final List<Interceptor> interceptors = new ArrayList<>();

	/**
	 * 添加插件
	 */
	public void addInterceptor(Interceptor interceptor) {
		interceptors.add(interceptor);
	}

	/**
	 * 对被拦截对象进行层层代理
	 */
	public Object pluginAll(Object target) {
		for (Interceptor interceptor : interceptors) {
			target = interceptor.plugin(target);
		}
		return target;
	}

	/**
	 * 判断是否存在插件
	 */
	public boolean hasPlugin() {
		if (interceptors.isEmpty()) {
			return false;
		}
		return true;
	}

}
