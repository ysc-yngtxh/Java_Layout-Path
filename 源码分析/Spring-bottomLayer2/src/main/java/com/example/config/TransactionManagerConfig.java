package com.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-26 23:43
 * @apiNote TODO
 */
@Configuration
@EnableTransactionManagement  // 开启事务管理
public class TransactionManagerConfig {

    // 定义数据源
    @Bean("transactionDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/springdb?useSSL=false&serverTimezone=UTC")
                .username("root")
                .password("131474")
                .build();
    }

    // 定义事务管理器
    @Bean
    public PlatformTransactionManager platformTransactionManager(@Qualifier("transactionDataSource") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    // 定义JdbcTemplate
    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("transactionDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
