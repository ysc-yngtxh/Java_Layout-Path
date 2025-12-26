package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
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

	// 服务器发送事件（SSE）示例：每秒发送一个随机数
	//    1、终端访问：curl -N -H "Accept: text/event-stream" http://localhost:8080/api/users/randomNumbers
	//    2、Postman：选择 GET 方法，输入 URL 后，在 Headers 中添加键值对 Accept: text/event-stream
	@GetMapping("/randomNumbers")
	public Flux<ServerSentEvent<Integer>> randomNumbers() {
		return Flux.interval(Duration.ofSeconds(1))
				.map(seq ->
						Tuples.of(seq, ThreadLocalRandom.current().nextInt())
				)
				.map(data ->
						ServerSentEvent.<Integer>builder()
								.event("random")
								.id(Long.toString(data.getT1()))
								.data(data.getT2())
								.build()
				);
	}



	// 全局异常处理
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(ex.getMessage());
	}

}
