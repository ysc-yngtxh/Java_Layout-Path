package com.example.reactive.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flux/users")
public class UserController {

	private final UserService userService;

	@GetMapping
	public Flux<User> getAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<User>> getUserById(@PathVariable Integer id) {
		return userService.findById(id)
		                  .map(ResponseEntity::ok)
		                  .defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/username/{username}")
	public Mono<ResponseEntity<User>> getUserByUsername(@PathVariable String username) {
		return userService.findByUsername(username)
		                  .map(ResponseEntity::ok)
		                  .defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<User> createUser(@RequestBody User user) {
		return userService.save(user);
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<User>> updateUser(@PathVariable Integer id, @RequestBody User user) {
		return userService.update(id, user)
		                  .map(ResponseEntity::ok)
		                  .defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Object>> deleteUser(@PathVariable Integer id) {
		return userService.deleteById(id)
		                  .then(Mono.just(ResponseEntity.noContent().build()))
		                  .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
	}

	// 全局异常处理
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(ex.getMessage());
	}
}
