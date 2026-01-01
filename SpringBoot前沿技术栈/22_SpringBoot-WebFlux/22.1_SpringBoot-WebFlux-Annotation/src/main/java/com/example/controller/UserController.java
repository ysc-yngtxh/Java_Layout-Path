package com.example.controller;

import com.example.pojo.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import javax.xml.stream.events.Comment;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;
	private final UserRepository userRepository;

    // TODO 注意：只有 @RequestParam、@PathVariable、@ModelAttribute 三种注解，才会触发格式化器（Formatter）与转换器（Converter）。
    //           而 @RequestBody 则不会触发它们，因为请求体的反序列化是由 HttpMessageConverter 负责的。

    // 使用 @RequestParam 接收 LocalDateTime 参数，触发 格式化器（Formatter）：
    //    curl --request GET --url 'http://localhost:8080/api/users/queryParam?created_date=2025-12-26%2023%3A30%3A00'
	@GetMapping("/queryParam1")
    public Flux<User> getAllUsers(@RequestParam("created_date") LocalDateTime createdDate) {
        log.info("LocalDataTime created_date: {}", createdDate);
		return userRepository.findAll();
	}

    // 使用 @RequestParam 接收 LocalDateTime 参数，触发 转换器（Converter）：
    //    curl --request GET --url 'http://localhost:8080/api/users/queryParam?created_date=2025-12-26T23%3A30%3A00'
    @GetMapping("/queryParam2")
    public Flux<User> getAllUsers(@RequestParam("created_date") Instant createdDate) {
        log.info("instant created_date: {}", createdDate);
        return userRepository.findAll();
	}


	@GetMapping
	public ResponseEntity<Void> flux2ListAsync() {
		userService.flux2ListAsync(userRepository.findAll());
        return ResponseEntity.ok().build();
    }

	@GetMapping("/cover")
	public Mono<ResponseEntity<User>> findAllMono() {
        // defaultIfEmpty()：当 Mono 为空时，返回一个默认值；
        return userService.findAllMono().map(ResponseEntity::ok)
		                  .defaultIfEmpty(ResponseEntity.notFound().build());
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
                                .comment("comment:" + data.getT1())
                                .data(data.getT2())
                                .build()
				);
	}

}
