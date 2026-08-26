package com.example.config;

import java.io.File;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.json.JsonMapper;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-30 22:00
 * @apiNote TODO
 */
@Configuration
public class RedisConfig {

	@Bean
	@SuppressWarnings("all")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);

        GenericJacksonJsonRedisSerializer jacksonJsonRedisSerializer = new GenericJacksonJsonRedisSerializer(new JsonMapper());
        jacksonJsonRedisSerializer.serialize(JsonMapper.builder().build());

        template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(jacksonJsonRedisSerializer);

		template.setHashKeySerializer(new GenericToStringSerializer<>(Integer.class));
		template.setHashValueSerializer(new GenericToStringSerializer<>(File.class));

		// 手动进行 Redis 初始化
		template.afterPropertiesSet();
		return template;
	}

}
