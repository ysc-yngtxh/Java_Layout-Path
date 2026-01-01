package com.example.service.impl;

import com.example.pojo.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public Flux<User> findAllFlux() {
        Flux<User> userFlux = userRepository.findAll();

        // collectList()：将 Flux 转换为 Mono<List<User>>
        // block()：阻塞当前线程，等待响应式流执行完成，直接获取包裹的 List<User> 结果；
        // timeout(Duration.ofSeconds(3))：设置超时时间为 3 秒，防止长时间等待；
        List<User> userList = userFlux.collectList().timeout(Duration.ofSeconds(3)).block();
        // blockOptional()：阻塞当前线程，等待响应式流执行完成，获取包裹的 List<User> 结果的 Optional；
        //                  避免 Flux<User> 为空，直接返回 null，增加空值兜底，避免后续空指针异常
        List<User> userList2 = userFlux.collectList().blockOptional().orElse(List.of(User.builder().userName("游家纨绔").build()));

        // TODO 上述 block() 是阻塞式写法，而 WebFlux 的设计核心是「非阻塞、异步响应式」，
        //      在 核心业务链路 中，强烈推荐使用纯响应式写法（无阻塞、高性能，适配高并发场景）。
        return Flux.fromIterable(userList2);
    }

    @Override
    public void flux2ListAsync(Flux<User> userFlux) {
        // ✅ 异步订阅，非阻塞，主线程立即返回
        userFlux.collectList()
                .subscribe(
                        // 成功回调：异步拿到List<User>结果
                        userList -> {
                            System.out.println("异步获取用户列表：" + userList);
                            // 后续业务逻辑（如入库、数据处理）
                        },
                        // 异常回调：处理流执行失败的情况
                        error -> System.err.println("转换失败：" + error.getMessage())
                );
    }

	@Override
	public Mono<User> findAllMono() {
        Flux<User> userFlux = userRepository.findAll();

        // collectList()：将 Flux 转换为 Mono<List<User>>
        return userFlux.collectList().map(List::getFirst);
	}


	@Override
	public Mono<User> findById(Integer id) {
        // 返回数据或空值
		return Mono.justOrEmpty(userRepository.findById(id).block());
	}

	@Override
	public Mono<User> findByUsername(String username) {
        // 返回数据或错误
		return userRepository
                .findByUserName(username)
				.switchIfEmpty(
						Mono.error(new RuntimeException("User not found with username: " + username))
				);
	}

	@Override
	public Mono<User> save(User user) {
		// 可以在这里添加业务验证逻辑
		return userRepository.save(user);
	}

	@Override
	public Mono<Void> saveProcess(User user) {
		// 添加业务处理逻辑，例如日志记录、通知等
		return userRepository.save(user)
                .doOnNext(System.out::println).thenEmpty(Mono.empty());
	}

	@Override
	public Mono<User> update(Integer id, User user) {
		return userRepository
                .findById(id)
		        .flatMap(item -> {
			        item.setUserName(user.getUserName());
			        item.setEmail(user.getEmail());
			        item.setFullName(user.getFullName());
			        item.setActive(user.getActive());
			        return userRepository.save(item);
		        })
		        .switchIfEmpty(
						 Mono.error(new RuntimeException("User not found with id: " + id))
				);
	}

	@Override
	public Mono<Void> deleteById(Integer id) {
		return userRepository
                .existsById(id)
		        .flatMap(exists -> {
			        if (exists) {
				        return userRepository.deleteById(id);
			        } else {
				        return Mono.error(new RuntimeException("User not found with id: " + id));
			        }
		        });
	}

}
