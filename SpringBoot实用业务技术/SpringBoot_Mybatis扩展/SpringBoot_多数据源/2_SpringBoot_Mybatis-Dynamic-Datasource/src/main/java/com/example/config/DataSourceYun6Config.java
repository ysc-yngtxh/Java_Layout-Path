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

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.mapper.yun6", sqlSessionTemplateRef = "yun6SqlSessionTemplate")
public class DataSourceYun6Config {

    // application.yml文件配置好之后，我们创建两个配置类来加载配置信息，初始化数据源
    // 1）类注解@MapperScan的属性basePackages配置的为对应DA层dao的位置
    // 2）@Primary注解指定了主数据源
    @Bean(name = "yun6DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.yun6")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "yun6SqlSessionFactory")
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("yun6DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "yun6SqlSessionTemplate")
    public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("yun6SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}