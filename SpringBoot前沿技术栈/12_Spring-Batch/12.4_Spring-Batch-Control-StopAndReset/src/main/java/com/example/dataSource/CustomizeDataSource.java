package com.example.dataSource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-01 22:40
 * @apiNote TODO 自定义数据资源配置类
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.datasource") // 需要set方法，将pProperties中的key值注入到相应的字段之中
public class CustomizeDataSource {

	private String driverClassName;
	private String url;
	private String username;
	private String password;

	// @Value("${spring.datasource.driver-class-name}")
	// private String driverClassName;
	//
	// @Value("${spring.datasource.url}")
	// private String url;
	//
	// @Value("${spring.datasource.username}")
	// private String username;
	//
	// @Value("${spring.datasource.password}")
	// private String password;

	@Bean
	@Primary //  自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
	public DataSource batchDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource(); // DataSource子类实例化
		hikariDataSource.setDriverClassName(driverClassName);
		hikariDataSource.setJdbcUrl(url);
		hikariDataSource.setUsername(username);
		hikariDataSource.setPassword(password);
		return hikariDataSource;
	}

	@Bean
	public PlatformTransactionManager batchTransactionManager(DataSource batchDataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(batchDataSource);
		return transactionManager;
	}
}
