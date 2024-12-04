package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                // 自定义授权同意页（解决 OAuth2 因网络原因导致授权同意页刷新缓慢的问题）
                .authorizationEndpoint(auth -> auth
                        .consentPage("/consent")
                )
                // OidcUserinfo 包含一组关于最终用户身份验证的 "标准声明（claims）"，这个类帮助生成最终 id_token 的数据。
                // 从 access_token 提取用户的基本数据，转换为 OidcUserInfo 对象
                .oidc(oidc ->
                        oidc.userInfoEndpoint(securityContextHolder ->
                                securityContextHolder.userInfoMapper(userInfo -> {
                                    // access_token
                                    JwtAuthenticationToken principal = (JwtAuthenticationToken) userInfo.getAuthentication().getPrincipal();
                                    // 将 access_token 信息转换 oidc 用户信息
                                    OidcUserInfo oidcUserInfo = OidcUserInfo.builder()
                                            .claims(claims ->
                                                    claims.putAll(principal.getToken().getClaims())
                                            )
                                            .email("youjiawanku@163.com") // 也可以自己包装信息
                                            .nickname("游家纨绔")
                                            .build();
                                    return oidcUserInfo;
                                })
                        )
                );    // Initialize `OidcConfigurer`

        // 配置异常处理器：将未认证的异常请求重定向到 /login
        http
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(
                                new LoginUrlAuthenticationEntryPoint("/login")
                        )
                )
                // TODO UserInfo 端点是Spring资源服务器中一项受保护的资源，需要手动启用。
                //      启用之后，OIDC 客户端就可以使用访问令牌对用户信息发起请求了。
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults())
                );

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/create/app").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .permitAll()
                );

        return http.build();
    }


    // TODO 以下三个 Bean 主要用于数据库持久化使用的。只不过需要传入 JdbcTemplate，当然也可以自定义

    // 返回基于数据库的 RegisteredClientRepository 接口实现类对象（对应 oauth2_registered_client 表)
    // 该表主要用于注册 授权使用的客户端。比如 gitee、github 上注册的授权应用
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    // 返回基于数据库的 OAuth2AuthorizationService 接口实现类对象（对应 oauth2_authorization 表）
    // 该表主要用于存储授权信息，比如用户授权给客户端的权限、授权码、访问令牌、刷新令牌等
    @Bean
    public OAuth2AuthorizationService oAuth2AuthorizationService(JdbcTemplate jdbcTemplate,
                                                                 RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    // 返回基于数据库的 OAuth2AuthorizationConsentService 接口实现类对象（对应 oauth2_authorization_consent 表）
    // 该表主要用于存储用户同意授权的信息，比如用户同意授权给客户端的权限
    @Bean
    public OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService(JdbcTemplate jdbcTemplate,
                                                                               RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }
}
