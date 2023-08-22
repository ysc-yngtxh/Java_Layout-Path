package com.example.springbootshirojwt.shiro;

import com.example.springbootshirojwt.filter.JwtFilter;
import com.example.springbootshirojwt.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    //shiro过滤工厂(请求都在这儿过滤)
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean FactoryBean = new ShiroFilterFactoryBean();
        FactoryBean.setSecurityManager(securityManager);


        System.out.println("Filter");
        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JwtFilter());
        FactoryBean.setFilters(filterMap);

        /*添加shiro的内置过滤器
         *    anon:无需认证就可以访问
         *    authc:必须认证了才能访问
         *    roles:拥有某个角色权限才能访问
         *    user:必须拥有 记住我 功能才能用
         *    perms:拥有对某个资源的权限才能访问
         * */

        Map<String,String> map = new LinkedHashMap<>();
        /*map.put("/user/chen","roles[vip]");
        map.put("/user/update","perms[user:update]");*/
        map.put("/login","anon"); // 登录不需要认证授权，只需要去数据库里校验用户名及密码正确即可
        map.put("/user/ysc","roles[svip]");
        map.put("/user/you","jwt");
        //map.put("/**","jwt"); // 一般自己定义的过滤器放最底下
        FactoryBean.setFilterChainDefinitionMap(map); // 将授权信息写入过滤器链中

        // 设置登陆的请求(未认证的请求会转到sgf页面)
        FactoryBean.setLoginUrl("/Authentication");
        // 设置未授权的页面
        FactoryBean.setUnauthorizedUrl("/Authorization");
        // 设置成功登陆的跳转页面
        // FactoryBean.setSuccessUrl("/user/you");

        return FactoryBean;
    }


    // 配置一个SecurityManager 安全管理器
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm") MyRealm myrealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myrealm);

        // 关闭shiro的session（无状态的方式使用shiro）
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }


    // 配置一个自定义的Realm的bean,最终将使用这个bean返回的对象完成我们的认证和授权
    @Bean
    public MyRealm myRealm(){

        return new MyRealm();
    }


    /*
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 下面的代码是添加注解支持
     * @param securityManager
     * @return
     */
    /*@Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;

    }*/
}
