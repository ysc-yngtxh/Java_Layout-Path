package com.example.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 */
@Controller
public class WebController {

	@RequestMapping(value = "/user/detail")
	public @ResponseBody Object userDetail() {
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("id", 1001);
		retMap.put("username", "lisi");
		return retMap;
	}

	@RequestMapping(value = "/user/page/detail")
	public String userPageDetail(Model model) {
		model.addAttribute("id", 1001);
		model.addAttribute("username", "WangWu");
		return "WEB-INF/userDetail";
	}

}
