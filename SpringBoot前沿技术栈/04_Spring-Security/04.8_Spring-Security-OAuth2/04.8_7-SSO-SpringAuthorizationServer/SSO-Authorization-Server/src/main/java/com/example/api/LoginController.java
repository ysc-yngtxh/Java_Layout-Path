package com.example.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 游家纨绔
 * @dateTime 2024-12-01 22:00
 * @apiNote TODO
 */
@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

}
