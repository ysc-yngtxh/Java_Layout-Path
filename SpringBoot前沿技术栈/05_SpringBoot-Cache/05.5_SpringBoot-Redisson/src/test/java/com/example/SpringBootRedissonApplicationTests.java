package com.example;

import com.example.service.RedissonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootRedissonApplicationTests {

	@Autowired
	private RedissonService redissonService;

	@Test
	void test1() {
		redissonService.lock("ConnectionWatchdog", 1000);
	}

	@Test
	void test2() {
		redissonService.fixeLock("ConnectionWatchdog", 1000);
	}
}
