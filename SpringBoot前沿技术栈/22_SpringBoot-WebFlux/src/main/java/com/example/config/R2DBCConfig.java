package com.example.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
// 启用 R2DBC 仓库，指定基础包路径
@EnableR2dbcRepositories(basePackages = "com.example.repository")
public class R2DBCConfig {
    //
    // @Bean
    // @Override
    // public ConnectionFactory connectionFactory() {
    //     // 使用内存模式的 H2 数据库（R2DBC URL）
    //     return ConnectionFactories.get("r2dbc:h2:mem:///testdb;DB_CLOSE_DELAY=-1");
    // }
    @Bean
    public ServletRegistrationBean<JakartaWebServlet> h2Console() {
        ServletRegistrationBean<JakartaWebServlet> bean =
                new ServletRegistrationBean<>(new JakartaWebServlet());

        bean.addUrlMappings("/h2-console/*");
        bean.setLoadOnStartup(1);

        // 初始化参数
        bean.addInitParameter("webAllowOthers", "true");
        bean.addInitParameter("trace", "true");

        return bean;
    }

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
}
