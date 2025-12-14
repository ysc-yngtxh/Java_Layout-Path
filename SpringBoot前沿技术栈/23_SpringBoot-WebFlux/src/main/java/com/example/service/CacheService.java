package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 缓存服务
 */
@Service
public class CacheService {

	@Autowired
	private ReactiveRedisTemplate<String, Object> redisTemplate;

	@Autowired
	private UserRepository userRepository;

	/**
	 * 设置缓存
	 */
	public Mono<Boolean> set(String key, Object value, Duration ttl) {
		return redisTemplate.opsForValue().set(key, value, ttl);
	}

	/**
	 * 获取缓存
	 */
	public <T> Mono<T> get(String key, Class<T> type) {
		return redisTemplate.opsForValue().get(key).cast(type);
	}

	/**
	 * 删除缓存
	 */
	public Mono<Boolean> delete(String key) {
		return redisTemplate.delete(key)
		                    .map(count -> count > 0);
	}

	/**
	 * 带缓存的数据访问
	 */
	public Mono<User> getUserWithCache(Long id) {
		String cacheKey = "user:" + id;

		return get(cacheKey, User.class).switchIfEmpty(
				userRepository.findById(id)
				              .flatMap(user ->
						                       set(cacheKey, user, Duration.ofMinutes(30)).thenReturn(user)
				              )
		        );
	}
}
