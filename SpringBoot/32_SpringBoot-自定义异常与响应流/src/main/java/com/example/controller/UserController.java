package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/1/{id}")
	// 标注状态码。即正常访问接口返回的状态码为：302
	@ResponseStatus(value = HttpStatus.FOUND, reason = "参数错误")
	public ResponseEntity<List<User>> queryUser1(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(userService.queryUser1(id));
	}

	@GetMapping("/2/{id}")
	public ResponseEntity<User> queryUser2(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(userService.queryUser2(id));
	}

	@GetMapping("/ysc")
	public ResponseEntity<List<User>> queryUser3(User user) {
		return ResponseEntity.ok(userService.queryUser3(user));
	}

	@PostMapping
	public ResponseEntity<String> saveUser1(User user) {
		userService.saveUser1(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("不好意思，添加商品失败！");
	}

	@PostMapping("/empty")
	public ResponseEntity<Void> saveUser2(User user) {
		userService.saveUser2(user);
		// 因为我们不需要返回值，所以可以不去写body，写上build会更合适
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
