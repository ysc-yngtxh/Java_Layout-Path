package com.example.dependency;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-08 22:58
 * @apiNote TODO 这个类表示外部依赖，只有添加了这个依赖，才能支持我们自定义的SPI扩展：CustomTemplate才能被注入
 */
@Configuration
@ConditionalOnProperty(value = "custom.spring.spi.enable", havingValue = "true")
public class EnableCustomDependency {

}
