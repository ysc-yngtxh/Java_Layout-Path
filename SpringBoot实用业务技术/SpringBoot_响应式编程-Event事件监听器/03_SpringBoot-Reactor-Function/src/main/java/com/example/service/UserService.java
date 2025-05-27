package com.example.service;

import com.example.pojo.User;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class UserService {

	// 模拟数据库存储
	private Map<Integer, User> map = new HashMap<>();
	private final AtomicInteger idGenerator = new AtomicInteger(3); // 初始值为3，因为已有3个用户

	public UserService() {
		map.put(1, new User("zhangsan"));
		map.put(2, new User("lisi"));
		map.put(3, new User("wangwu"));
	}

	// 根据id查询
	public Mono<User> getUserById(Integer id) {
		// 返回数据或空值
		return Mono.justOrEmpty(map.get(id));
	}

	// 查询多个
	public Flux<User> getAll() {
		return Flux.fromIterable(map.values());
	}

	// 创建用户
	public Mono<User> createUser(User user) {
		return Mono.fromCallable(() -> {
			           // 生成新ID（线程安全）
			           int newId = idGenerator.incrementAndGet();
			           // 存入Map
			           map.put(newId, user);
			           // 返回创建的用户
			           return user;
		           })
		           // 确保操作在弹性调度器上执行，避免阻塞响应式线程
		           .subscribeOn(Schedulers.boundedElastic());
	}

	// 保存
	public Mono<Void> save(Mono<User> userMono) {
		return userMono.doOnNext(user -> {
			int id = map.size() + 1;
			map.put(id, user);
		}).thenEmpty(Mono.empty()); // 最后置空
	}

}
