package com.wen3.oauth.ss.resourceserver.autoconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author tangheng
 */
@Configuration
public class ResourceServerAutoConfiguration {

    @Bean
    public SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry = http.authorizeHttpRequests();
        authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();

        OAuth2ResourceServerConfigurer<HttpSecurity> oAuth2ResourceServerConfigurer = http.oauth2ResourceServer();
//        oAuth2ResourceServerConfigurer.jwt();
        oAuth2ResourceServerConfigurer.bearerTokenResolver(new DefaultBearerTokenResolver());
        oAuth2ResourceServerConfigurer.opaqueToken();

        return http.build();
    }
}