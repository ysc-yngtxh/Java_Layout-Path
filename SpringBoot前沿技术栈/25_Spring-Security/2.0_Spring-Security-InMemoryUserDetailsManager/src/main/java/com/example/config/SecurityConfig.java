package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-17 07:54
 * @apiNote TODO 配置类
 */
@Configuration
public class SecurityConfig {

    /**
     * 这里去定义SpringSecurity的密码校验算法，默认是SHA-256，我们这里取消校验算法，直接进行比对
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 通过缓存类过滤器，去实现我们的自定义用户。
     * SpringSecurity要求必须至少有一种权限，这里先各自给上一种
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(
                User.withUsername("admin").password("admin").authorities("admin").build(),
                User.withUsername("manager").password("manager").authorities("manager").build()
        );
        return userDetailsManager;
    }
}
