package com.example.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-29 23:00
 * @apiNote TODO 登录后需要对应角色才能访问的接口
 */
@Controller
@RequestMapping("/result")
public class AnnotationAuthorityController {

	@RequestMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public @ResponseBody String user() {
		return "你好啊，小猫咪！";
	}

	@RequestMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody String admin() {
		return "第三次世界大战！";
	}

	@RequestMapping("/vip")
	@PreAuthorize("hasRole('VIP')")
	public @ResponseBody String vip() {
		return "我爱吃热干面！";
	}
}
