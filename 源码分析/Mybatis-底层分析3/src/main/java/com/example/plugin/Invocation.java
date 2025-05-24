package com.example.plugin;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.SneakyThrows;

/**
 * 包装类，对被代理对象进行包装
 */
@Getter
@AllArgsConstructor
public class Invocation {

	private Object target;
	private Method method;
	private Object[] args;

	@SneakyThrows
	public Object proceed() {
		return method.invoke(target, args);
	}

}
