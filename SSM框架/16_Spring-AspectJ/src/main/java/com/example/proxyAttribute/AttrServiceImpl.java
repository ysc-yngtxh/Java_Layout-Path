package com.example.proxyAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-25 12:40:00
 * @apiNote TODO
 */
@Service("attrService")
public class AttrServiceImpl {

	@Autowired
	private InjectServiceImpl injectServiceImpl;

	public void doSomething() {
		System.out.println("AttrServiceImpl获取属性：" + injectServiceImpl.getClass());
	}
}
