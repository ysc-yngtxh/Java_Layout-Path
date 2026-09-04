package com.example.service.impl;

import com.example.service.StudentService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 */
@Service
public class StudentServiceImpl implements StudentService {

	// 因为RedisTemplate这个bean的key默认是Object类型的，在依赖注入的时候，想将key改为String类型，问题就出在了这里。
	// @Autowired注解是根据类型来将bean注入的 RedisTemplate<Object, Object>，
	// 而我写的是RedisTemplate<String, Object>，根据类型，Spring容器中没有找到，所以就会报错了；
	// 如果用@Resource的这个注解是根据名称在Spring容器中寻找bean的，所以没有问题.
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	// 这一个类是redis依赖提供的，就在Maven的spring-data-redis

	@Override
	public void put(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public String get(String count) {
		String query = (String) redisTemplate.opsForValue().get(count);

		Map<String, String> hashMap = new HashMap<>(2);
		hashMap.put("redis", "redis");
		hashMap.put("mysql", "mysql");
		for (Map.Entry<String, String> keyValue : hashMap.entrySet()) {
			redisTemplate.opsForHash().put("key", keyValue.getKey(), keyValue.getValue());
		}
		return query;
	}
}
