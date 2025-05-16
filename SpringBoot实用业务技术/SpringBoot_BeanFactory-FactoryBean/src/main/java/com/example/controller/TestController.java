package com.example.controller;

import com.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private TestService testService;

	@GetMapping("/doService")
	public void doService() {
		// 因为实现了FactoryBean接口，所以testService实现的是FactoryBean接口中getObject()的返回值：EnhanceServiceImpl
		testService.doService();
	}
}
