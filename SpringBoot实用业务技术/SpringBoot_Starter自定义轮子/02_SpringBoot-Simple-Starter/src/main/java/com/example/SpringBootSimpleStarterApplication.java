package com.example;

import com.example.service.IpCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootSimpleStarterApplication {

	@Autowired
	private IpCountService ipCountService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSimpleStarterApplication.class, args);
	}

	@RequestMapping("/test")
	public String test() {
		ipCountService.IpCount();
		return "自定义的轮子 -- 需要调用！";
	}

	@RequestMapping("/test1")
	public String test1() {
		return "自定义的轮子 -- 开箱即用！";
	}
}
