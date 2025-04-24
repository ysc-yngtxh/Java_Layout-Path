package com.example.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-04-21 14:13:48
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.redisson")
public class RedissonProperties {

	private String config;

	private String file;
}
