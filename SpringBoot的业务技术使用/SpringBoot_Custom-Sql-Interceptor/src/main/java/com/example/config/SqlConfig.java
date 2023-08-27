package com.example.config;

import com.example.executor.ParameterInterceptor;
import com.example.executor.ResultInterceptor;
import com.example.executor.StatementInterceptor;
import com.example.interceptor.MyInterceptor;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-25 15:08
 * @apiNote TODO
 */
@Configuration
public class SqlConfig implements WebMvcConfigurer {
    String[] addPath = {
            "/tbUser/**"
    };
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new MyInterceptor() )
                .addPathPatterns(addPath)
                .excludePathPatterns();
    }

    // 第二种方式：将自定义的拦截器添加进 mybatis 配置中
    @Resource
    private SqlSessionFactory sqlSessionFactory;
    @PostConstruct
    public void addInterceptor() {
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.addInterceptor(new ParameterInterceptor());
        configuration.addInterceptor(new StatementInterceptor());
        configuration.addInterceptor(new ResultInterceptor());
    }
}
