package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-23 18:43
 * @apiNote TODO 配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 使用 Lambda DSL
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // TODO 注意：更新springboot版本3.1.0后。已经不支持再继续使用.csrf().disable()、and()这种类似写法了
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .formLogin(Customizer.withDefaults())
            // .formLogin((formLogin) ->
            //         formLogin
            //                 .usernameParameter("username")
            //                 .passwordParameter("password")
            //                 .loginPage("/authentication/login")
            //                 .failureUrl("/authentication/login?failed")
            //                 .loginProcessingUrl("/authentication/login/process"));
            // TODO 这里定义 Oauth2 的登录配置
            .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
