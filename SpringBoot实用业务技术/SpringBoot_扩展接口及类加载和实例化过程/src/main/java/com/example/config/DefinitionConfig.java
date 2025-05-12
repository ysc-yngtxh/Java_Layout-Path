package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-05-12 18:00:00
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class DefinitionConfig {

	private String url;

	private String name;

	private String desc;

	private String customComponentScanPackages;
}
