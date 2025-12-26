package com.example;

import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootWebFluxAnnotationApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		userRepository.findAll()
				.doOnNext(user -> System.out.println("User: " + user))
				.blockFirst();
	}

}
