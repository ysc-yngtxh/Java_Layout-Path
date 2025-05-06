package com.example.config;

import com.example.interceptor.ParameterInterceptor;
import com.example.interceptor.ResultInterceptor;
import com.example.interceptor.StatementInterceptor;
import com.example.tenant.TenantContextHolderInterceptor;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-25 15:00
 * @apiNote TODO
 */
@Configuration
public class SqlPostConstructConfig implements WebMvcConfigurer {

    String[] addPath = {
            "/user/**"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TenantContextHolderInterceptor())
                .addPathPatterns(addPath)
                .excludePathPatterns();
    }

    // 第二种方式：将自定义的拦截器添加进 mybatis 配置中
    @Resource
    private SqlSessionFactory sqlSessionFactory;
    @Resource
    private LockTables lockTables;
    @PostConstruct
    public void addInterceptor() {
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        // 最后添加的会更早执行
        configuration.addInterceptor(new StatementInterceptor(lockTables));
        configuration.addInterceptor(new ParameterInterceptor());
        configuration.addInterceptor(new ResultInterceptor());
    }
}
