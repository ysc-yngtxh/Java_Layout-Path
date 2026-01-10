package com.example.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @desc TODO 登陆后接口
 * @date 2023-04-07 上午10:10:00
 */
@RestController
public class SayHelloController {

	@RequestMapping("/sayHello")
	public String hello() {
		return "十年生死两茫茫，不思量，自难忘";
	}


	// 测试 SpringSecurity加盐值的加密算法
	public static void main(String[] args) {
		// 使用BcryptPasswordEncoder加密,需要注意的是，这样加密的话，盐值是随机的，最终结果每次都会不一样
		BCryptPasswordEncoder bcyEncoder = new BCryptPasswordEncoder();
		System.out.println(bcyEncoder.encode("123456"));

		// 所以应该通过自己制定盐值的方法来生成加密(盐值有规则：不想看规则的就--长度不下于28位，开头以'$2$10$'[不是必须的开头，但万能])
		String password = "123456";
		String encode = BCrypt.hashpw(password, "$2$10$shichengqwertyuioplkjhgfdsa");
		System.out.println(encode);
	}
}
