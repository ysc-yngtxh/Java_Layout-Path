package com.example.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 配置一个Shiro的过滤器bean,这个bean将配置shiro相关的一个规则的拦截
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        // 创建过滤器配置Bean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/"); // 配置用户登录请求  如果需要进行登录时Shiro就会转到这个请求进入登陆页面
        shiroFilterFactoryBean.setSuccessUrl("/success"); // 配置登陆成功以后转向的请求地址
        shiroFilterFactoryBean.setUnauthorizedUrl("/noPermission"); // 配置没有权限时转向的请求地址

        // 权限拦截
        Map<String, String> filterChainMap = new LinkedHashMap<>();

        /*添加shiro的内置过滤器
         *    anon:无需认证就可以访问
         *    authc:必须认证了才能访问
         *    role:拥有某个角色权限才能访问
         *    user:必须拥有 记住我 功能才能用
         *    perms:拥有对某个资源的权限才能访问
         * */
        filterChainMap.put("/login", "anon");    // 配置登录请求不需要认证 anon表示某个请求不需要认证
        filterChainMap.put("/logout", "logout"); // 配置登录的请求，登出后会请求当前用户的内存

        // 这后面注释的代码都是可以通过加注解的方式实现
        // filterChainMap.put("/admin/**", "authc");配置一个user开头的所有的请求需要登陆 authc表示需要登录认证
        // filterChainMap.put("/user/**", "authc");配置一个user开头的所有的请求需要登陆 authc表示需要登录认证

        // authc,roles[admin] 表示所有以admin开头的请求需要有admin的角色才可以使用
        // filterChainMap.put("/admin/**", "authc, roles[admin]");
        // filterChainMap.put("/user/**", "authc, roles[admin]");

        // 配置剩余的所有请求全部需要进行登录认证(注意：这个必须写在最后面)：可选的配置
        // filterChainMap.put("/**", "authc");

        // 设置权限拦截规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }


    // 配置一个SecurityManager 安全管理器
    @Bean
    public SessionsSecurityManager securityManager(MyRealm myrealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myrealm);
        return defaultWebSecurityManager;
    }

    // 配置一个自定义的Realm的bean,最终将使用这个bean返回的对象完成我们的认证和授权
    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }

    //*** 千万别忘了加 bean, 否则页面无法正常的显示shiro标签权限 !!! ***//
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
