package com.example.aop1;

/**
 * @author 游家纨绔
 */
public class OneServiceImpl implements OneService {

	@Override
	public void doSome(String name, Integer age) {
		System.out.println("====目标方法doSome()====");
	}
}
