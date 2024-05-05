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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.mapper.springdb", sqlSessionTemplateRef = "springdbSqlSessionTemplate")
public class DataSourceSpringDbConfig {

    // application.yml文件配置好之后，我们创建两个配置类来加载配置信息，初始化数据源
    //   1）类注解@MapperScan的属性basePackages配置的为对应DA层dao的位置
    //   2）@Primary注解修改优先权，表示发现相同类型bean，优先使用该方法。
    @Primary
    @Bean(name = "springdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.springdb") // 指向yml配置文件中的数据库配置
    public DataSource blogDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "springdbSqlSessionFactory")
    public SqlSessionFactory blogSqlSessionFactory(@Qualifier("springdbDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 这个的getResources指向的是你的mapper.xml文件，相当于在yml中配置的mapper-locations，此处配置了yml中就不用配置，或者说不会读取yml中的该配置。
        // bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "springdbSqlSessionTemplate")
    public SqlSessionTemplate blogSqlSessionTemplate(@Qualifier("springdbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
