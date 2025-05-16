package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class OidcClientAutoConfig {

	// 负责处理从授权服务器获取到的用户信息，并将其转换为应用程序可以使用的用户对象。
	// 该类有两个实现类：DefaultOAuth2UserService、OidcUserService
	@Autowired
	private OAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService;

	@Bean
	public SecurityFilterChain authorizationClientSecurityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
						                       .anyRequest().authenticated()
				                      )
				.oauth2Login(oauth2 ->
						             // 自定义配置OIDC提供的用户信息端点。
						             oauth2.userInfoEndpoint(userInfo ->
								                                     userInfo.oidcUserService(oAuth2UserService)
						                                    )
				            );

		return http.build();
	}
}
