package com.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 数据源配置类
 */
@Configuration
public class DataSourceManagerConfig {

    /**
     * 定义数据源bean
     */
    @Bean
    // @ConfigurationProperties("spring.datasource")  // 绑定配置文件中的数据源属性
    public DataSource dataSource() {
        // TODO 数据库连接的数据源实现
        // 1、DriverManagerDataSource：（过时）
        //    最基本的数据源实现，它直接利用JDBC的 DriverManager 来获取数据库连接。
        //    当调用 DriverManager.getConnection(url,username,password) 时，它会根据提供的URL自动动态选择正确的驱动，然后由该驱动创建连接。
        //    没有连接池功能，意味着每次请求连接都会创建一个新的数据库连接，然后在连接不再使用时关闭。
        //    由于其低效的资源管理和潜在的性能瓶颈，DriverManagerDataSource 通常只建议在测试环境下使用，不适合生产环境。
        // 2、SimpleDriverDataSource：（不支持连接池，测试环境推荐使用）
        //    它是Spring框架提供的一个简单数据源实现，允许直接使用JDBC驱动来创建连接，而不是通过 DriverManager。
        //    即没有调用 DriverManager.getConnection(url,username,password) 来进行动态选择驱动，而是直接通过 Driver 创建连接。
        //    也不包含连接池功能，每次请求连接时都会创建一个新的数据库连接，这在生产环境中可能导致性能问题，但它适合于测试或轻量级应用，尤其是在需要避免类加载冲突的情况下。
        // 3、DataSourceBuilder.create()：(支持连接池，生产环境使用)
        //    这是Spring Boot中用于动态构建数据源的一个便捷方法，它可以基于应用程序的配置自动选择或创建合适的数据源实现。
        //    使用它可以很容易地配置连接池（如HikariCP、Tomcat JDBC Pool等），只需在配置文件中指定相应的属性（如spring.datasource.*），或者在代码中指定详细配置。
        //    DataSourceBuilder.create()提供了高度的灵活性和自动化，特别适合于需要高级数据源配置和连接池优化的Spring Boot应用。

        // DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        // dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://127.0.0.1:3306/springdb?useUnicode=true&characterEncoding=utf-8")
                .username("root")
                .password("131474")
                // 可以通过 type 配置相应的类或属性来指定连接池的实现，例如HikariCP、Tomcat JDBC Pool、Apache DBCP等。
                .type(HikariDataSource.class)
                .build();
    }

    /**
     * 定义事务管理bean
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        // 注入dataSource
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    /**
     * 配置JdbcTemplate组件
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        // 注入dataSource
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
}