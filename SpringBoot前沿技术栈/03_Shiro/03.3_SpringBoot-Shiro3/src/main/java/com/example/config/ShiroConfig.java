package com.example.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.realm.RealmUser;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

	// 3、ShiroFilterFactoryBean   shiro过滤工厂(请求都在这儿过滤)
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		// 设置安全管理器
		bean.setSecurityManager(defaultWebSecurityManager);

        // 1. 设置登录页（即认证失败后重定向的页面）
        bean.setLoginUrl("/toLogin");       // 登录页地址(未认证的请求会转到登录页面)
        bean.setUnauthorizedUrl("/noauto"); // 未授权页面

		/* 添加shiro的内置过滤器
		 *    anon:  无需认证就可以访问
		 *    authc: 必须认证了才能访问
		 *    role:  拥有某个角色权限才能访问
		 *    user:  必须拥有用户功能才能用
		 *    perms: 拥有对某个资源的权限才能访问
		 * */
		Map<String, String> Map = new LinkedHashMap<>();

        // 所有不需要认证的路径必须明确声明为 anon
        Map.put("/toLogin", "anon");
        Map.put("/login",   "anon");    // 登录提交接口
        Map.put("/",        "anon");
        Map.put("/index",   "anon");
        Map.put("/noauto",  "anon");
        // 静态资源（如果有）也要放行，例如：
        // Map.put("/css/**", "anon");
        // Map.put("/js/**", "anon");

		// 设置认证或授权的路径，只有授权了才能访问
		Map.put("/user/update", "perms[user:update]");  // 授权要写在认证前面，否则会发生安全穿透
		// 必须认证了才能访问
		// Map.put("/user/*", "authc");

		bean.setFilterChainDefinitionMap(Map);

		return bean;
	}


	// 2、defaultWebSecurityManager  安全管理器
	@Bean
	public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("realmUser") RealmUser realmUser) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 关联RealmUser
		securityManager.setRealm(realmUser);
		return securityManager;
	}


	// 1、创建Realm对象(授权认证)
	@Bean
	public RealmUser realmUser() {
		return new RealmUser();
	}


	// 整合ShiroDialect: 用来整合shiro、thymeleaf
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}
}
