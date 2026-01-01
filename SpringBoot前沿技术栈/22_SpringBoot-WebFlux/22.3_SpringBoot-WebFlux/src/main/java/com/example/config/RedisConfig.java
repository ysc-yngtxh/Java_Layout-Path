package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;

/**
 * Redis响应式配置
 */
@Configuration
public class RedisConfig {

	@Bean
	public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {

		StringRedisSerializer keySerializer = new StringRedisSerializer();
        // JacksonJsonRedisSerializer<Object> valueSerializer = new JacksonJsonRedisSerializer<>(Object.class);
		GenericToStringSerializer<Object> valueSerializer = new GenericToStringSerializer<>(Object.class);

		RedisSerializationContext<String, Object> context =
				RedisSerializationContext
                        .<String, Object>newSerializationContext(keySerializer)
                        .value(valueSerializer)
                        .build();

		return new ReactiveRedisTemplate<>(connectionFactory, context);
	}

}
