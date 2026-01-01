package com.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.File;

/**
 * 描述：redis配置类
 * redis序列化方式选择：
 * 1、(默认的)JdkSerializationRedisSerializer序列化方式，其编码为 ISO-8859-1，会出现乱码问题；
 * 2、StringRedisSerializer序列化方式，其编码为 UTF-8，可以解决乱码问题；
 */
@Configuration
public class RedisConfig {

	// 初始化一个 RedisTemplate<Object, Object> 对象
	@Bean
	@SuppressWarnings("all")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		// 获取Redis的连接操作对象
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);

		// 使用Jackson的序列化
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		jackson2JsonRedisSerializer.setObjectMapper(new ObjectMapper());

		RedisSerializer stringSerializer = new StringRedisSerializer();

		// 设置Redis的String数据类型key部分，序列化方式为 StringRedisSerializer
		template.setKeySerializer(stringSerializer);
		// 设置Redis的String数据类型value部分，序列化方式为 Jackson2JsonRedisSerializer
		template.setValueSerializer(jackson2JsonRedisSerializer);

		// 设置Redis的hash数据类型key部分，序列化方式为 StringRedisSerializer
		template.setHashKeySerializer(stringSerializer);
		// 设置Redis的hash数据类型value部分，序列化方式为 Jackson2JsonRedisSerializer
		template.setHashValueSerializer(jackson2JsonRedisSerializer);

		// 推荐使用 GenericToStringSerializer 作为类型序列化，比Jackson2JsonRedisSerializer兼容性更好
		// template.setHashKeySerializer(new GenericToStringSerializer<>(Integer.class));
		// template.setHashValueSerializer(new GenericToStringSerializer<>(File.class));

		// Redis初始化
		template.afterPropertiesSet();
		return template;
	}
}
