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
		System.out.println(userRepository.findByUserName("张三大爷"));
	}

}
