package com.example.config;

import io.r2dbc.spi.ConnectionFactory;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;

import static org.springframework.data.r2dbc.dialect.MySqlDialect.INSTANCE;

@Configuration
// 启用 R2DBC 仓库，指定基础包路径
@EnableR2dbcRepositories(basePackages = "com.example.repository")
public class R2DBCConfig {

	@Bean
	public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
		return new R2dbcEntityTemplate(connectionFactory);
	}

	// 或者使用 DatabaseClient
	@Bean
	public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
		return DatabaseClient.builder()
		                     .connectionFactory(connectionFactory)
		                     .build();
	}

	// 配置自定义转换器
	@Bean
	public R2dbcCustomConversions customConversions() {
		return new R2dbcCustomConversions(
				INSTANCE,  // 使用 MySQL 方言
				Arrays.asList(
						new BooleanToByteConverter(),
						new ByteToBooleanConverter()
				             )
		);
	}
}
