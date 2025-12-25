package com.example.controller;

import com.example.pojo.Users;
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
	public Flux<Users> getAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<Users>> getUserById(@PathVariable Integer id) {
		return userService.findById(id)
		                  .map(ResponseEntity::ok)
		                  .defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/username/{username}")
	public Mono<ResponseEntity<Users>> getUserByUsername(@PathVariable String username) {
		return userService.findByUsername(username)
		                  .map(ResponseEntity::ok)
		                  .defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Users> createUser(@RequestBody Users users) {
		return userService.save(users);
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<Users>> updateUser(@PathVariable Integer id, @RequestBody Users users) {
		return userService.update(id, users)
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
