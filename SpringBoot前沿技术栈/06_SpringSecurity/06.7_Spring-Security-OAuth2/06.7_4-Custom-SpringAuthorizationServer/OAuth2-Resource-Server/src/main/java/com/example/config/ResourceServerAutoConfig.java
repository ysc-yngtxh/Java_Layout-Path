package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-23 18:43
 * @apiNote TODO 配置类
 */
@Configuration
@EnableWebSecurity
public class ResourceServerAutoConfig {
    //
    // @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    // public String issuerUri;
    
    @Bean
    public SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth2 -> auth2
                    .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2Resource -> oauth2Resource
                    // .jwt(jwt -> jwt.decoder(JwtDecoders.fromIssuerLocation(issuerUri))));
                    .jwt()
            );
        return http.build();
    }
}
