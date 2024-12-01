package com.example.config;

import com.example.oauth2.handler.OAuth2SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-23 18:43
 * @apiNote TODO SpringSecurity、OAuth2配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 。
    @Bean
    public PasswordEncoder passwordEncoder() {
        // NoOpPasswordEncoder 表示不进行任何密码编码的密码编码器，但是明文存储密码是极其不安全，因此官方标记已过时。
        // 在实际项目中，应该使用更安全的密码编码器，如 BCryptPasswordEncoder。
        return NoOpPasswordEncoder.getInstance();
    }

    // 创建了一个用户名为 "admin"、密码为 "123456" 且具有 "ADMIN" 角色的用户，并将其存储在内存中。
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password("123456")
                        .roles("ADMIN")
                        .build()
        );
    }

    // OAuth2 登录成功处理器
    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;
    // 这里引用的是容器中 OAuth2UserService 的实现类，也就是 OAuth2UserServiceImpl。
    // 负责处理从授权服务器获取到的用户信息，并将其转换为应用程序可以使用的用户对象。
    @Autowired
    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService;
    // 定义用户拥有的角色和权限。权限信息通常以 GrantedAuthority(表示用户被授予的权限) 的形式
    // 可参考 SpringSecurity 中的 UserDetails 类中的 getAuthorities() 方法。
    @Autowired
    private GrantedAuthoritiesMapper grantedAuthoritiesMapper;

    /**
     * 使用 Lambda DSL
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                .formLogin(Customizer.withDefaults())
                // .formLogin((formLogin) ->
                //         formLogin
                //                 .usernameParameter("username")
                //                 .passwordParameter("password")
                //                 .loginPage("/authentication/login")
                //                 .failureUrl("/authentication/login?failed")
                //                 .loginProcessingUrl("/authentication/login/process"));
                // TODO 这里定义 Oauth2 的登录配置
                // authorizationEndpoint、redirectionEndpoint、tokenEndpoint 是 Oauth2 所规范的端点
                // userInfoEndpoint 则是 OIDC 所定义的端点
                .oauth2Login(oauth2 ->
                        oauth2
                                // .loginPage("/login") // 指定登录页面

                                // TODO 1、定义 OAuth2 登录成功后跳转的路径
                                .defaultSuccessUrl("/oauth2/gitee", true)
                                // .successHandler(oAuth2SuccessHandler) // 自定义成功处理器
                                // .failureHandler(oAuth2FailureHandler) // 自定义失败处理器

                                // TODO 2、自定义授权端点。默认 URI 为：/oauth2/authorization
                                .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
                                        .baseUri("/oauth2/authorization")
                                )

                                // TODO 3、自定义重定向端点。默认 URI 为：login/oauth2/code/*，这里多加一个 * 是为了不局限于只匹配单个路径
                                .redirectionEndpoint(redirectionEndpoint -> redirectionEndpoint
                                        .baseUri("/login/oauth2/code/**")
                                )

                                // TODO 4、配置 OAuth2 访问令牌的端点。可以指定访问令牌 URI 的模板和其他相关属性。
                                .tokenEndpoint(tokenEndpoint -> tokenEndpoint
                                        .accessTokenResponseClient(accessTokenResponseClient())
                                )

                                // TODO 5、自定义用户信息端点。
                                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                        // TODO 5.1、处理第三方授权服务器 OAuth2 用户信息。（可将用户信息存入数据库）
                                        .userService(oauth2UserService)
                                        // TODO 5.2、处理 OAuth2 用户的访问权限
                                        .userAuthoritiesMapper(grantedAuthoritiesMapper)
                                )

                                // 用于指定客户端注册仓库。可以自定义客户端注册仓库来管理多个 OAuth2 提供商的配置。
                                .clientRegistrationRepository(clientRegistrationRepository())

                                // 用于指定授权客户端服务。可以自定义授权客户端服务来管理用户的授权信息。
                                .authorizedClientService(authorizedClientService())

                                // 用于配置是否允许所有用户访问 OAuth2 登录相关的端点。默认情况下，这些端点是允许所有用户访问的。
                                .permitAll(true)
                );
                // .oauth2Client(oauth2 -> oauth2
                //         .clientRegistrationRepository(clientRegistrationRepository())
                //         .authorizedClientService(authorizedClientService())
                //         .authorizationCodeGrant(authorizationCodeGrant -> authorizationCodeGrant
                //                 .accessTokenResponseClient(accessTokenResponseClient())
                //                 .authorizationRequestResolver(authorizationRequestResolver())
                //                 .authorizationRequestCustomizer(authorizationRequestCustomizer -> authorizationRequestCustomizer
                //                         .additionalParameters(parameters -> parameters.put("custom_param", "value"))
                //                 )
                //         )
                // );
        return http.build();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        // 自定义访问令牌响应客户端
        return new DefaultAuthorizationCodeTokenResponseClient();
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        // 自定义授权客户端服务
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        // 自定义客户端注册仓库
        return new InMemoryClientRegistrationRepository(giteeClientRegistration());
    }

    private ClientRegistration giteeClientRegistration() {
        // 配置客户端注册信息 -- (会覆盖住 yml 文件中 Aauth2 相关配置)
        return ClientRegistration.withRegistrationId("gitee")
                .clientName("Gitee")
                .clientId("8def619da68a212d02a36d471cef229ab3b80c81222e76ed2e581de76f9a6d0a")
                .clientSecret("1f5276f0abd9c8b8b5eee50356ca912d61e62a59d0b8ad285dc49b4879b0ddad")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                // 注意：这里的 {baseUrl} 可能是 http://127.0.0.1:8080。与 http://localhost:8080 有所不同。
                // 可以通过在 yml 文件中指定 server.address 属性来解决
                .redirectUri("{baseUrl}/{action}/oauth2/code/{registrationId}")
                .scope("pull_requests", "emails", "user_info")
                .authorizationUri("https://gitee.com/oauth/authorize")
                .tokenUri("https://gitee.com/oauth/token")
                .userInfoUri("https://gitee.com/api/v5/user")
                .userNameAttributeName("name")
                .build();
    }



    // @Bean
    // public OAuth2AuthorizationRequestResolver authorizationRequestResolver() {
    //     // 自定义授权请求解析器
    //     return new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository(), OAuth2AuthorizationRequest.REDIRECT_ACTION);
    // }

    // @Bean
    // public OAuth2AuthorizationRequestCustomizer authorizationRequestCustomizer() {
    //     // 自定义授权请求
    //     return OAuth2AuthorizationRequestCustomizer.withDefaults();
    // }

    // @Bean
    // public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
    //     // 自定义登录入口点
    //     return new LoginUrlAuthenticationEntryPoint("/login");
    // }
}
