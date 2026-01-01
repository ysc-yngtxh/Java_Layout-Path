package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class DemoApplicationConfiguration {
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password("{noop}123456").authorities("ROLE_ACTIVITI_USER").build(),
                User.withUsername("manager").password("{noop}131474").authorities("ROOT").build()
        );
    }

}
