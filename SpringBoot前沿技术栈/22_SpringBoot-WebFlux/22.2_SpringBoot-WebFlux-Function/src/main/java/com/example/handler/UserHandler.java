package com.example.handler;

import com.example.dto.UserDto;
import com.example.pojo.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserHandler {

	private final UserRepository userRepository;
	private final ConversionService conversionService;

	// 获取所有用户
	public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        request.queryParam("created_date").map(
                dateStr -> {
                    // 手动使用转换器
                    LocalDateTime localDateTime = conversionService.convert(dateStr, LocalDateTime.class);
                    Instant date = conversionService.convert(localDateTime, Instant.class);
                    System.out.println("⚪️Converter 手动转换结果: " + date);
                    return date;
                }
        ).ifPresent(System.out::println);
        return ServerResponse.ok()
		                     .contentType(MediaType.APPLICATION_JSON)
		                     .body(userRepository.findAll(), User.class);
	}

	// 根据 ID获取用户
	public Mono<ServerResponse> getUserById(ServerRequest request) {
		// 获取请求路径中的id值
		Integer id = Integer.parseInt(request.pathVariable("id"));
		// 空值处理
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		// 调用Service方法得到数据
		Mono<User> userMono = userRepository.findById(id);
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
		Mono<User> userMono = request.bodyToMono(User.class);
		// 调用Service保存用户
		return userMono
				.flatMap(userRepository::save)
				.flatMap(savedUser ->
					ServerResponse.status(HttpStatus.CREATED)
							.contentType(MediaType.APPLICATION_JSON)
							.bodyValue(savedUser)
				);
	}

	// 更新用户
	public Mono<ServerResponse> updateUser(ServerRequest request) {
		// 获取请求路径中的id值
		Integer id = Integer.parseInt(request.pathVariable("id"));
		// 获取请求体中的User对象
		Mono<User> userMono = request.bodyToMono(User.class);

		return userMono
				.flatMap(userRepository::save)
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
		return userRepository.deleteById(id)
		                  .then(ServerResponse.noContent().build())
		                  .onErrorResume(e -> ServerResponse.notFound().build());
	}

}
