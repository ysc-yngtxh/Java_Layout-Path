package com.example;

import com.example.entity.TbBrand;
import com.example.service.ReadWriteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootReadWriteSplittingApplicationTests {

	@Autowired
	private ReadWriteService readWriteService;

	@Test
	void contextLoads() {
		System.out.println(readWriteService.getUserByBrand("小米"));
	}

	@Test
	void contextLoads2() {
		System.out.println(readWriteService.listAllBrand());
	}

}
