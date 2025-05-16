package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 游家纨绔
 * @Description TODO 测试日志的Controller
 * @Date 2025-03-07 17:00:00
 */
@RestController
public class LogStashController {

	/**
	 * 获取日志输出对象
	 */
	private static final Logger log = LoggerFactory.getLogger(LogStashController.class);

	/**
	 * 测试输出log的访问方法
	 */
	@GetMapping("/LogStash")
	public void testLog() {
		log.error("测试输出一个日志");
	}
}
