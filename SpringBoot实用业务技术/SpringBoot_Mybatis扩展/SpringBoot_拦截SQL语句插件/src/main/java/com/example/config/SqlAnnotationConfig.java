package com.example.config;

import com.example.interceptor.ExecutorTenantInterceptor;
import com.example.interceptor.ExecutorWhereInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SqlAnnotationConfig implements ApplicationContextAware {

    /** Aware接口及其子接口
     *    会在refreshContext()方法中实例化所有bean实例(可以获取到所有的bean实例对象)，调用Aware子接口的接口方法。
     *    用来获取Spring启动时相关的对象，在项目启动后需要用到时直接调用
     */

    // 第一种方式：将自定义的拦截器添加进 Mybatis 配置中，配合注解使用
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);

        sqlSessionFactory.getConfiguration().addInterceptor(new ExecutorTenantInterceptor());

        AllowTables allowTables = applicationContext.getBean(AllowTables.class);
        sqlSessionFactory.getConfiguration().addInterceptor(new ExecutorWhereInterceptor(allowTables));
    }
}
