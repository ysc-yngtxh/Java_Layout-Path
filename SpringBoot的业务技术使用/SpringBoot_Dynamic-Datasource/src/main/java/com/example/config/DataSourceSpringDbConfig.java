package com.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.mapper.springdb", sqlSessionTemplateRef = "springdbSqlSessionTemplate")
public class DataSourceSpringDbConfig {

    @Primary
    @Bean(name = "springdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.springdb")
    public DataSource blogDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "springdbSqlSessionFactory")
    public SqlSessionFactory blogSqlSessionFactory(@Qualifier("springdbDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "springdbSqlSessionTemplate")
    public SqlSessionTemplate blogSqlSessionTemplate(@Qualifier("springdbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
