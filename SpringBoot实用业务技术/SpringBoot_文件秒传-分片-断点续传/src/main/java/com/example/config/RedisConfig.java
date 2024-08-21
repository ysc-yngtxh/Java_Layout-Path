package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("localhost");
        config.setPort(6379);
        config.setPassword(RedisPassword.of(""));
        return new LettuceConnectionFactory(config);
    }

    @Bean
    public ThreadLocal<RedisConnection> redisConnectionThreadLocal(RedisConnectionFactory redisConnectionFactory) {
        return ThreadLocal.withInitial(() -> redisConnectionFactory.getConnection());
    }

}