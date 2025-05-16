package com.example.config;

import javax.sql.DataSource;
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

@Configuration
// @MapperScan 的 sqlSessionTemplateRef 属性指定了每个Mapper接口使用的模板。
// 并且通过 basePackages 属性将不同包的Mapper绑定到不同数据源，以实现动态多数据源的支持。
@MapperScan(basePackages = "com.example.mapper.business2", sqlSessionTemplateRef = "business2SqlSessionTemplate")
public class DataSourceBusiness2Config {

	// application.yml文件配置好之后，我们创建两个配置类来加载配置信息，初始化数据源
	//   1）类注解 @MapperScan 的属性basePackages配置的为对应 DAO层mapper接口 的位置
	//   2）@Primary注解修改优先权，表示发现相同类型bean，优先使用该方法。
	@Primary
	@Bean(name = "business2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.slave")
	public DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "business2SqlSessionFactory")
	public SqlSessionFactory userSqlSessionFactory(@Qualifier("business2DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	@Bean(name = "business2SqlSessionTemplate")
	public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("business2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
