package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 基于注解的Controller（与Spring MVC类似）
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 返回单个用户（Mono）
	 */
	@GetMapping("/{id}")
	public Mono<User> getUser(@PathVariable Long id) {
		return userService.findById(id);
	}

	/**
	 * 返回用户列表（Flux）
	 */
	@GetMapping
	public Flux<User> getAllUsers() {
		return userService.findAll();
	}

	/**
	 * 创建用户
	 */
	@PostMapping
	public Mono<User> createUser(@RequestBody User user) {
		return userService.save(user);
	}

	/**
	 * 更新用户
	 */
	@PutMapping("/{id}")
	public Mono<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		return userService.update(id, user);
	}

	/**
	 * 删除用户
	 */
	@DeleteMapping("/{id}")
	public Mono<Void> deleteUser(@PathVariable Long id) {
		return userService.deleteById(id);
	}
}
