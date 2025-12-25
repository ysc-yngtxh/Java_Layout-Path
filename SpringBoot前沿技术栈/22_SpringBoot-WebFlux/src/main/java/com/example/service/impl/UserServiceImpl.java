package com.example.service.impl;

import com.example.pojo.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public Flux<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Mono<User> findById(Integer id) {
		return userRepository.findById(id)
		                     .switchIfEmpty(Mono.error(new RuntimeException("User not found with id: " + id)));
	}

	@Override
	public Mono<User> save(User user) {
		// 可以在这里添加业务验证逻辑
		return userRepository.save(user);
	}

	@Override
	public Mono<User> update(Integer id, User user) {
		return userRepository.findById(id)
		                     .flatMap(existingUser -> {
			                     existingUser.setUsername(user.getUsername());
			                     existingUser.setEmail(user.getEmail());
			                     existingUser.setFullName(user.getFullName());
			                     existingUser.setActive(user.isActive());
			                     return userRepository.save(existingUser);
		                     })
		                     .switchIfEmpty(Mono.error(new RuntimeException("User not found with id: " + id)));
	}

	@Override
	public Mono<Void> deleteById(Integer id) {
		return userRepository.existsById(id)
		                     .flatMap(exists -> {
			                     if (exists) {
				                     return userRepository.deleteById(id);
			                     } else {
				                     return Mono.error(new RuntimeException("User not found with id: " + id));
			                     }
		                     });
	}

	@Override
	public Mono<User> findByUsername(String username) {
		return userRepository.findByUsername(username)
		                     .switchIfEmpty(Mono.error(new RuntimeException("User not found with username: " + username)));
	}

}
