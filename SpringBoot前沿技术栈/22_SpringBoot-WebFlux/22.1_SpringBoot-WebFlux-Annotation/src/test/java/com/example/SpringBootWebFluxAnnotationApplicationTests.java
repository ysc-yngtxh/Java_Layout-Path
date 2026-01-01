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
        // doOnNext()：在每个元素被发出时执行一个副作用操作，这里用于打印每个用户信息。
        // blockFirst()：阻塞等待第一个元素的到来，适用于测试场景。
		userRepository.findAll()
				.doOnNext(user -> System.out.println("User: " + user))
				.blockFirst();
	}

}
