package com.example.dependency;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-08 22:00
 * @apiNote TODO 这个类表示外部依赖，只有添加了这个依赖，才能支持我们自定义的SPI扩展：CustomTemplate才能被注入
 */
@Data
@Configuration
@ConfigurationProperties("custom.spring.spi")
@ConditionalOnProperty(value = "custom.spring.spi.enable", havingValue = "true")
public class EnableCustomDependency {

	private String enable;
}
