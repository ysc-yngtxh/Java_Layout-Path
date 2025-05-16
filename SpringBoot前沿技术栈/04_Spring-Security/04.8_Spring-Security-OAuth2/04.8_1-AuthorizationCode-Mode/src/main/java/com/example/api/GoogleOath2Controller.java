package com.example.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-03 09:00
 * @apiNote TODO
 */
@Controller
public class GoogleOath2Controller {

	/**
	 * google 请求授权页面
	 */
	@RequestMapping("/google/auth")
	public @ResponseBody String googleAuth() {
		return "Google是国外网站，因访问受限，这里暂不实现Google的授权码模式👀";
	}
}
