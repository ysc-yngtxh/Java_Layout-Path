package com.example;

import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootH2ApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		System.out.println("根据 userName 查询 H2 数据：" + userRepository.findByUserName("张三"));
		System.out.println("查询 H2 所有数据：" + userRepository.findAll());
	}

}
