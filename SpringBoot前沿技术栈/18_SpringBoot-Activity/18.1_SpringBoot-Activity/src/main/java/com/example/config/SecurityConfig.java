package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
public class SecurityConfig {

    // Activiti8 内置了 SpringSecurity 安全框架，需要配置 UserDetailsService Bean。
    @Bean
    public UserDetailsService myUserDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        //这里添加用户，后面处理流程时用到的任务负责人，需要添加在这里
        String[][] usersGroupsAndRoles = {
                {"Make", "{noop}123456", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
                {"Tony", "{noop}123456", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
                {"George", "{noop}123456", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
                {"other", "{noop}123456", "ROLE_ACTIVITI_USER", "GROUP_otherTeam"},
                {"system", "{noop}123456", "ROLE_ACTIVITI_USER"},
                {"游家纨绔", "{noop}123456", "ROLE_ACTIVITI_ADMIN"},
        };

        for (String[] user : usersGroupsAndRoles) {
            List<String> authoritiesStrings = Arrays.asList(Arrays.copyOfRange(user, 2, user.length));
            log.info("> Registering new user: {} with the following Authorities[{}]", user[0], authoritiesStrings);
            inMemoryUserDetailsManager.createUser(
                    new User(
                            user[0],
                            passwordEncoder().encode(user[1]),
                            authoritiesStrings.stream().map(SimpleGrantedAuthority::new).toList()
                    )
            );
        }

        return inMemoryUserDetailsManager;
    }

    // SpringSecurity密码编码器配置，这里使用NoOpPasswordEncoder，表示不进行任何编码（仅适合用于测试环境）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
