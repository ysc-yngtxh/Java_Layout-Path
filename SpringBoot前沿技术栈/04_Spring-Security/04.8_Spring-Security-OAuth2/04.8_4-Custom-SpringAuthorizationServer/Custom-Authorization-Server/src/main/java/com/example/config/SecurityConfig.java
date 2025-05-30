package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	@Order(1)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		// 授权服务器的安全交给 SpringSecurity 的过滤器处理
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		// 自定义授权服务器的配置
		http
				.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
				// 自定义授权同意页（解决 OAuth2 因网络原因导致授权同意页刷新缓慢的问题）
				.authorizationEndpoint(auth -> auth
						                       .consentPage("/consent")
				                      )
				.oidc(Customizer.withDefaults());    // Initialize `OidcConfigurer`

		// 配置异常处理器：将未认证的异常请求重定向到 /login
		http
				.exceptionHandling(ex -> ex
						                   .authenticationEntryPoint(
								                   new LoginUrlAuthenticationEntryPoint("/login")
						                                            )
				                  );

		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize ->
						                       authorize.requestMatchers("/static/**").permitAll()
						                                .anyRequest().authenticated()
				                      )
				.formLogin(form -> form
						           .loginPage("/login")
						           .loginProcessingUrl("/login")
						           .permitAll()
				          );

		return http.build();
	}
}
