package com.example.controller;

import com.example.domain.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 游家纨绔
 */
@Controller
public class UserController {

	// 循环遍历list集合
	@RequestMapping("/user")
	public String each(Model model) {
		List<User> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setId(100 + i);
			user.setNick("张" + i);
			user.setPhone("15623041568" + i);
			user.setAddress("武汉江夏区" + i);
			list.add(user);
		}
		model.addAttribute("list", list);

		return "each";
	}

	// 循环遍历Map集合
	@RequestMapping("/each")
	public String each1(Model model) {
		Map<Integer, Object> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setId(100 + i);
			user.setNick("张" + i);
			user.setPhone("15623041568" + i);
			user.setAddress("武汉江夏区" + i);
			map.put(i, user);
		}
		model.addAttribute("map", map);

		return "eachMap";
	}

	// 循环遍历复杂集合
	@RequestMapping("/each/all")
	public String each2(Model model) {
		List<Map<Integer, List<User>>> myList = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			Map<Integer, List<User>> myMap = new HashMap<>();

			for (int j = 0; j < 2; j++) {
				List<User> myUserList = new ArrayList<>();

				for (int k = 0; k < 3; k++) {
					User user = new User();
					user.setId(100 + k);
					user.setNick("张" + k);
					user.setPhone("15623041568" + k);
					user.setAddress("武汉江夏区" + k);
					myUserList.add(user);
				}
				myMap.put(j, myUserList);
			}
			myList.add(myMap);
		}
		model.addAttribute("myList", myList);

		return "eachAll";
	}
}
