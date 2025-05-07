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
// @MapperScan 的 sqlSessionTemplateRef 属性指定了每个Mapper接口使用的模板。
// 并且通过 basePackages 属性将不同包的Mapper绑定到不同数据源，以实现动态多数据源的支持。
@MapperScan(basePackages = "com.example.mapper.business", sqlSessionTemplateRef = "businessSqlSessionTemplate")
public class DataSourceBusiness1Config {

    // application.yml文件配置好之后，我们创建两个配置类来加载配置信息，初始化数据源
    //   1）类注解 @MapperScan 的属性basePackages配置的为对应 DAO层mapper接口 的位置
    //   2）@Primary注解修改优先权，表示发现相同类型bean，优先使用该方法。
    @Primary
    @Bean(name = "businessDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master") // 指向yml配置文件中的数据库配置
    public DataSource blogDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "businessSqlSessionFactory")
    public SqlSessionFactory blogSqlSessionFactory(@Qualifier("businessDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 这个的getResources指向的是你的mapper.xml文件，相当于在yml中配置的mapper-locations，此处配置了yml中就不用配置，或者说不会读取yml中的该配置。
        // bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "businessSqlSessionTemplate")
    public SqlSessionTemplate blogSqlSessionTemplate(@Qualifier("businessSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
