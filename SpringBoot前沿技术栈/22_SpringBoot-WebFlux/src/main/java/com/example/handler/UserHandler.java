package com.example.handler;

import com.example.pojo.Users;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

	private final UserService userService;

	public UserHandler(UserService userService) {
		this.userService = userService;
	}

	// 获取所有用户
	public Mono<ServerResponse> getAllUsers(ServerRequest request) {
		return ServerResponse.ok()
		                     .contentType(MediaType.APPLICATION_JSON)
		                     .body(userService.findAll(), Users.class);
	}

	// 根据ID获取用户
	public Mono<ServerResponse> getUserById(ServerRequest request) {
		// 获取请求路径中的id值
		Integer id = Integer.parseInt(request.pathVariable("id"));
		// 空值处理
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		// 调用Service方法得到数据
		Mono<Users> userMono = userService.findById(id);
		return userMono
				.flatMap(user ->
						         ServerResponse.ok()
						                       .contentType(MediaType.APPLICATION_JSON)
						                       .bodyValue(user)
						                    // .body(BodyInserters.fromValue(userMono))
				        )
				.switchIfEmpty(notFound);
	}

	// 创建用户
	public Mono<ServerResponse> createUser(ServerRequest request) {
		// 获取请求体中的User对象
		Mono<Users> userMono = request.bodyToMono(Users.class);
		// 调用Service保存用户
		return userMono
				.flatMap(user -> userService.save(user))
				.flatMap(savedUser -> ServerResponse.status(HttpStatus.CREATED)
				                                    .contentType(MediaType.APPLICATION_JSON)
				                                    .bodyValue(savedUser));
	}

	// 更新用户
	public Mono<ServerResponse> updateUser(ServerRequest request) {
		// 获取请求路径中的id值
		Integer id = Integer.parseInt(request.pathVariable("id"));
		// 获取请求体中的User对象
		Mono<Users> userMono = request.bodyToMono(Users.class);

		return userMono
				.flatMap(user -> userService.update(id, user))
				.flatMap(updatedUser -> ServerResponse.ok()
				                                      .contentType(MediaType.APPLICATION_JSON)
				                                      .bodyValue(updatedUser))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	// 删除用户
	public Mono<ServerResponse> deleteUser(ServerRequest request) {
		// 获取请求路径中的id值
		Integer id = Integer.parseInt(request.pathVariable("id"));
		// 调用Service删除用户
		return userService.deleteById(id)
		                  .then(ServerResponse.noContent().build())
		                  .onErrorResume(e -> ServerResponse.notFound().build());
	}

}
