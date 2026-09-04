package com.example.service.impl;

import com.example.annotation.Validation;
import com.example.service.ContainInterfaceService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-26 13:10:00
 * @apiNote TODO
 */
@Service
public class ContainInterfaceServiceImpl implements ContainInterfaceService {

	@Override
	@Validation
	public String definition() {
		return "你好！这里是definition()方法执行的返回结果";
	}

	@Override
	public String aopFailure() {
		// 按理说，这里调用了Definition()方法，会执行其切面方法。但实际并没有进入切面。
		this.definition();
		return "你好！这里是aopFailure()方法执行的返回结果";
	}

	@Override
	public String aopContextExecutor() {
		ContainInterfaceServiceImpl currentProxy = (ContainInterfaceServiceImpl) AopContext.currentProxy();
		// 这里获取的当前类的代理对象，通过代理对象执行Definition()，才会进行到切面逻辑
		currentProxy.definition();
		return "你好！这里是aopContextExecutor()方法执行的返回结果";
	}
}
