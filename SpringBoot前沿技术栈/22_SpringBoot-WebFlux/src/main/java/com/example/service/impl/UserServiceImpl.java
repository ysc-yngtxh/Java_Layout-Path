package com.example.service.impl;

import com.example.pojo.Users;
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
	public Flux<Users> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Mono<Users> findById(Integer id) {
		return userRepository.findById(id)
		                     .switchIfEmpty(Mono.error(new RuntimeException("Users not found with id: " + id)));
	}

	@Override
	public Mono<Users> save(Users users) {
		// 可以在这里添加业务验证逻辑
		return userRepository.save(users);
	}

	@Override
	public Mono<Users> update(Integer id, Users users) {
		return userRepository.findById(id)
		                     .flatMap(existingUser -> {
			                     existingUser.setUserName(users.getUserName());
			                     existingUser.setEmail(users.getEmail());
			                     existingUser.setFullName(users.getFullName());
			                     existingUser.setActive(users.getActive());
			                     return userRepository.save(existingUser);
		                     })
		                     .switchIfEmpty(Mono.error(new RuntimeException("Users not found with id: " + id)));
	}

	@Override
	public Mono<Void> deleteById(Integer id) {
		return userRepository.existsById(id)
		                     .flatMap(exists -> {
			                     if (exists) {
				                     return userRepository.deleteById(id);
			                     } else {
				                     return Mono.error(new RuntimeException("Users not found with id: " + id));
			                     }
		                     });
	}

	@Override
	public Mono<Users> findByUsername(String username) {
		return userRepository.findByUserName(username)
		                     .switchIfEmpty(Mono.error(new RuntimeException("Users not found with username: " + username)));
	}

}
