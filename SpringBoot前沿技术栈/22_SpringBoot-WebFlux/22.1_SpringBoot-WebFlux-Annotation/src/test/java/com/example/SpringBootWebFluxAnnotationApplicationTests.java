package com.example;

import com.example.controller.UserController;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;

@SpringBootTest
@WebFluxTest(UserController.class)
class SpringBootWebFluxAnnotationApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads2() {
		userRepository.findAll()
				.doOnNext(user -> System.out.println("User: " + user))
				.blockFirst();
	}

}
