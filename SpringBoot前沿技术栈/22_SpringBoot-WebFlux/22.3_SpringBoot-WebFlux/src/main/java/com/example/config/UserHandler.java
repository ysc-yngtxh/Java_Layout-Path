package com.example.config;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Handler处理类
 */
@Component
public class UserHandler {

	@Autowired
	private UserService userService;

	/**
	 * 获取单个用户
	 */
	public Mono<ServerResponse> getUser(ServerRequest request) {
		Long id = Long.parseLong(request.pathVariable("id"));

		return userService.findById(id)
		                  .flatMap(user -> ServerResponse.ok()
		                                                 .contentType(MediaType.APPLICATION_JSON)
		                                                 .bodyValue(user))
		                  .switchIfEmpty(ServerResponse.notFound().build());
	}

	/**
	 * 获取所有用户
	 */
	public Mono<ServerResponse> getAllUsers(ServerRequest request) {
		Flux<User> users = userService.findAll();

		return ServerResponse.ok()
		                     .contentType(MediaType.APPLICATION_JSON)
		                     .body(users, User.class);
	}

	/**
	 * 创建用户
	 */
	public Mono<ServerResponse> createUser(ServerRequest request) {
		return request.bodyToMono(User.class)
		              .flatMap(user -> userService.save(user))
		              .flatMap(savedUser -> ServerResponse
				                       .status(HttpStatus.CREATED)
				                       .contentType(MediaType.APPLICATION_JSON)
				                       .bodyValue(savedUser)
		                      );
	}

	/**
	 * 更新用户
	 */
	public Mono<ServerResponse> updateUser(ServerRequest request) {
		Long id = Long.parseLong(request.pathVariable("id"));

		return request.bodyToMono(User.class)
		              .flatMap(user -> userService.update(id, user))
		              .flatMap(updatedUser -> ServerResponse.ok()
		                                                    .contentType(MediaType.APPLICATION_JSON)
		                                                    .bodyValue(updatedUser))
		              .switchIfEmpty(ServerResponse.notFound().build());
	}

	/**
	 * 删除用户
	 */
	public Mono<ServerResponse> deleteUser(ServerRequest request) {
		Long id = Long.parseLong(request.pathVariable("id"));

		return userService.deleteById(id).then(ServerResponse.noContent().build());
	}

	/**
	 * 获取统计信息
	 */
	public Mono<ServerResponse> getStatus(ServerRequest request) {
		return userService.getStatus()
		                  .flatMap(status -> ServerResponse.ok()
		                                                   .contentType(MediaType.APPLICATION_JSON)
		                                                   .bodyValue(status)
		                  );
	}

	/**
	 * 批量创建
	 */
	public Mono<ServerResponse> batchCreate(ServerRequest request) {
		Flux<User> users = request.bodyToFlux(User.class);

		return userService.saveAll(users)
		                  .collectList()
		                  .flatMap(savedUsers -> ServerResponse
				                           .status(HttpStatus.CREATED)
				                           .bodyValue(savedUsers)
		                  );
	}
}
