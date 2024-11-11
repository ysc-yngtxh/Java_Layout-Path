package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-23 18:43
 * @apiNote TODO 配置类
 */
@Configuration
@EnableWebSecurity
public class ResourceServerAutoConfig {

    @Bean
    public SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth2 -> auth2.anyRequest().authenticated())
            .oauth2ResourceServer().jwt();
        return http.build();
    }
}
