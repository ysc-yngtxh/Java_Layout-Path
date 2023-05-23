package com.wen3.oauth.ss.authclient.autoconfigure;

import com.wen3.oauth.ss.authclient.handler.DemoBearerTokenAuthenticationEntryPoint;
import com.wen3.oauth.ss.authclient.processor.OAuth2AuthorizationRequestRedirectFilterPostProcessor;
import com.wen3.oauth.ss.authclient.processor.OAuth2LoginAuthenticationFilterPostProcessor;
import com.wen3.oauth.ss.authclient.processor.SpringOpaqueTokenIntrospectorPostProcessor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author tangheng
 */
@Configuration
public class Oauth2ClientAutoConfiguration {

    @Resource
    private ClientRegistrationRepository clientRegistrationRepository;
    @Resource
    private SpringOpaqueTokenIntrospectorPostProcessor springOpaqueTokenIntrospectorPostProcessor;
    @Resource
    private AuthorizationManager authorizationManager;

    @Bean
    public SecurityFilterChain authorizationClientSecurityFilterChain(HttpSecurity http) throws Exception {
        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry =  http.authorizeHttpRequests();
        authorizationManagerRequestMatcherRegistry.requestMatchers(
                new AntPathRequestMatcher("/authorized"),
                new AntPathRequestMatcher("/error"),
//                        new AntPathRequestMatcher("/webjars/**"),
//                        new AntPathRequestMatcher("/resources/**"),
//                        new AntPathRequestMatcher("/index/**"),
//                        new AntPathRequestMatcher("/**/*.css"),
                new AntPathRequestMatcher("/**/*.ico")
        ).permitAll();
//        authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
        authorizationManagerRequestMatcherRegistry.anyRequest().access(authorizationManager);
//        authorizationManagerRequestMatcherRegistry.anyRequest().access(new AuthenticatedAuthorizationManager<>());

        http.logout();

        SessionManagementConfigurer<HttpSecurity> securitySessionManagementConfigurer = http.sessionManagement();
        securitySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        OAuth2LoginConfigurer<HttpSecurity> oAuth2LoginConfigurer = http.oauth2Login();
        oAuth2LoginConfigurer.authorizationEndpoint(c->{

        });
        oAuth2LoginConfigurer.tokenEndpoint(c->{

        });
        oAuth2LoginConfigurer.addObjectPostProcessor(new OAuth2AuthorizationRequestRedirectFilterPostProcessor());
        oAuth2LoginConfigurer.addObjectPostProcessor(new OAuth2LoginAuthenticationFilterPostProcessor());

//        OAuth2ClientConfigurer<HttpSecurity> oAuth2ClientConfigurer = http.oauth2Client();

        OAuth2ResourceServerConfigurer<HttpSecurity> oAuth2ResourceServerConfigurer = http.oauth2ResourceServer();
        oAuth2ResourceServerConfigurer.bearerTokenResolver(new DefaultBearerTokenResolver());
        OAuth2ResourceServerConfigurer.OpaqueTokenConfigurer opaqueTokenConfigurer = oAuth2ResourceServerConfigurer.opaqueToken();
        oAuth2ResourceServerConfigurer.authenticationEntryPoint(new DemoBearerTokenAuthenticationEntryPoint(clientRegistrationRepository));
        oAuth2ResourceServerConfigurer.addObjectPostProcessor(springOpaqueTokenIntrospectorPostProcessor);

        http.formLogin();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("test")
                .password("test")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}