package com.example.service;

public interface StudentService {

	// 将值存放到redis中
	void put(String key, String value);

	// 从redis中获取值
	String get(String count);
}
