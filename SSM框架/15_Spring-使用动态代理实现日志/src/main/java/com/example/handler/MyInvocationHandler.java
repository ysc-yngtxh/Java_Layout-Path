package com.example.handler;

import com.example.utill.ServiceTools;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object target;

	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object res = null;
		String methodName = method.getName();
		System.out.println("method名称：" + methodName);

		// 通过代理对象执行方法时，会调用执行这个invoke()
		if (!"doSome".equals(methodName)) {
			ServiceTools.doLog();
			// 执行目标类的方法，通过Method类实现
			res = method.invoke(target, args);
			ServiceTools.doTrans();
		} else {
			res = method.invoke(target, args);
		}
		return res;
	}
}
