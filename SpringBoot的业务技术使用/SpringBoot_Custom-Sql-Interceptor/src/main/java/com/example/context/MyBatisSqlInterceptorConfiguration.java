package com.example.context;

import com.example.executor.ExecutorInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyBatisSqlInterceptorConfiguration implements ApplicationContextAware {

    // 第一种方式：将自定义的拦截器添加进 mybatis 配置中
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);
        sqlSessionFactory.getConfiguration().addInterceptor(new ExecutorInterceptor());
    }
}