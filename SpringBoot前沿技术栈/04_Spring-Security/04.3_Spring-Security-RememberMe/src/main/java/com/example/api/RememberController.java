package com.example.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2024-12-12 22:10:00
 * @apiNote TODO
 */
@Controller
public class RememberController {

	@RequestMapping("/remember")
	public @ResponseBody String remember() {
		return "<h1>remember-me</h1>";
	}
}
