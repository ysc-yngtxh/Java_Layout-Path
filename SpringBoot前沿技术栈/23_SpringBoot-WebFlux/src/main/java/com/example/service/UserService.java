package com.example.service;

import com.example.entity.User;
import com.example.pojo.UserStatus;
import com.example.repository.UserRepository;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 用户服务
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Flux<User> findAll() {
		return userRepository.findAll();
	}

	public Mono<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public Mono<User> save(User user) {
		return userRepository.save(user);
	}

	public Flux<User> saveAll(Flux<User> users) {
		return userRepository.saveAll(users);
	}

	public Mono<Void> deleteById(Long id) {
		return userRepository.deleteById(id);
	}

	public Mono<User> update(Long id, User user) {
		return userRepository.findById(id)
		                     .flatMap(existing -> {
			                     existing.setName(user.getName());
			                     existing.setEmail(user.getEmail());
			                     existing.setAge(user.getAge());
			                     return userRepository.save(existing);
		                     });
	}

	public Mono<UserStatus> getStatus() {
		return userRepository.findAll()
		                     .collect(Collectors.groupingBy(
									 u -> u.getAge() / 10 * 10,  // 按年龄段分组
				                     Collectors.counting()
		                             )
		                     )
		                     .map(UserStatus::new);
	}
}
