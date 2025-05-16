package com.example.jdk;

import org.springframework.stereotype.Service;

@Service("jdkService")
public class JdkServiceImpl implements JdkService {

	@Override
	public String like(String name, Integer time) {
		System.out.println("=====like()方法的执行====");
		return "小曹，我like你!";
	}
}
