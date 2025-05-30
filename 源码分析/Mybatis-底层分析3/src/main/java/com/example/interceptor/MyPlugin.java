package com.example.interceptor;

import com.example.annotation.Intercepts;
import com.example.plugin.Interceptor;
import com.example.plugin.Invocation;
import com.example.plugin.Plugin;

import java.util.Arrays;

/**
 * 自定义插件
 */
@Intercepts("query")
public class MyPlugin implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) {
		String statement = (String) invocation.getArgs()[0];      // 获取SQL语句
		Object[] parameter = (Object[]) invocation.getArgs()[1];  // 获取参数
		Class<?> returnType = (Class<?>) invocation.getArgs()[2]; // 获取返回类型
		System.out.println("进入自定义插件：MyPlugin");
		System.out.println("SQL：[" + statement + "]");
		System.out.println("Parameters：" + Arrays.toString(parameter));
		System.out.println("ReturnType：" + returnType.getName());

		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

}
