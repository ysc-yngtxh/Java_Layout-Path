package com.example.config;

import com.mysql.cj.jdbc.Driver;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-26 23:43
 * @apiNote TODO
 */
@Configuration
@EnableTransactionManagement  // 开启注解事务管理，等同于xml配置方式的 <tx:annotation-driven/>
public class TransactionManagerConfig {

    // 定义数据源
    @SneakyThrows
    @Bean("transactionDataSource")
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new Driver());
        dataSource.setUrl("jdbc:mysql://localhost:3306/springdb?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("131474");
        return dataSource;
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
