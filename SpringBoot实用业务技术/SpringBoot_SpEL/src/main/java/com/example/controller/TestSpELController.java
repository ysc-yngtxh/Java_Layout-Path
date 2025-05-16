package com.example.controller;

import com.example.annotation.GetVal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-07 22:00
 * @apiNote TODO 解析文本
 */
@RestController
public class TestSpELController {

	// TODO 使用自定义的解析器上下文ParserContext进行解析表达式
	@RequestMapping("/test1")
	@GetVal(value = "@{'Hello World -- '.toUpperCase() + #name}")
	public String test1(@RequestParam String name) {
		return "SpEL表达式结果：HELLO WORLD -- " + name;
	}

	@RequestMapping("/test2")
	@GetVal(value = "'Hello World -- '.toUpperCase() + #name")
	public String test2(@RequestParam String name) {
		return "SpEL表达式结果：'Hello World -- '.toUpperCase() + #name";
	}


	// TODO 不使用解析器上下文ParserContext，进行默认解析表达式
	@RequestMapping("/test3")
	@GetVal(value = "@{'Hello World -- '.toUpperCase() + #name}", enable = "false")
	public String test3(@RequestParam String name) {
		// 很明显，这个接口报错。因为使用的默认解析表达式中的前后缀为 #{} 符号
		return "程序报错500！！！";
	}

	@RequestMapping("/test4")
	@GetVal(value = "'Hello World -- '.toUpperCase() + #name", enable = "false")
	public String test4(@RequestParam String name) {
		return "SpEL表达式结果：HELLO WORLD -- " + name;
	}
}
