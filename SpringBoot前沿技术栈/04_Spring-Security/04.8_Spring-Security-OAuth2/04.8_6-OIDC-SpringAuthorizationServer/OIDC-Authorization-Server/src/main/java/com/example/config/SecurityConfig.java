package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

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
        // 1. 实例化 OAuth2AuthorizationServerConfigurer
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer();

        // 2. 应用配置器并添加自定义配置
        http
                .apply(authorizationServerConfigurer)
                // 自定义授权同意页（解决 OAuth2 因网络原因导致授权同意页刷新缓慢的问题）
                .authorizationEndpoint(auth -> auth
                        .consentPage("/consent")
                )
                // 启用 OIDC 并自定义 userInfo 映射
                .oidc(oidc ->
                        oidc.userInfoEndpoint(userInfo ->
                                userInfo.userInfoMapper(userInfoContext -> {
                                    // 从认证对象中获取 JWT 令牌
                                    JwtAuthenticationToken principal =
                                            (JwtAuthenticationToken) userInfoContext.getAuthentication().getPrincipal();
                                    // 构建 OidcUserInfo，将 JWT claims 作为基础，并添加自定义信息
                                    return OidcUserInfo.builder()
                                            .claims(claims -> {
                                                if (principal != null) {
                                                    claims.putAll(principal.getToken().getClaims());
                                                }
                                            })
                                            .email("youjiawanku@163.com")
                                            .nickname("游家纨绔")
                                            // .claim("nickname", "游家纨绔")   // 还可以使用 claim() 方法
                                            .build();
                                })
                        )
                );  // Initialize `OidcConfigurer`

        // 3. 配置异常处理：未认证请求重定向到 /login
        http.exceptionHandling(ex -> ex
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
        );

        // 4. 启用资源服务器（JWT 解析）
        // TODO UserInfo 端点是Spring资源服务器中一项受保护的资源，需要手动启用。
        //      启用之后，OIDC 客户端就可以使用访问令牌对用户信息发起请求了。
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        // 5. 确保所有请求都需要认证（但授权服务器端点已由内部 securityMatcher 保护）
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                PathPatternRequestMatcher.withDefaults().matcher("/static/**"),
                                PathPatternRequestMatcher.withDefaults().matcher("/create/app")
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .permitAll()
                );

        return http.build();
    }

    // 配置授权服务器的一些基本设置：重新配置用户信息uri
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("https://localhost:9090") // 设置授权服务器地址
                .oidcUserInfoEndpoint("/custom/userinfo") // 设置自定义 user-info endpoint
                .build();
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
