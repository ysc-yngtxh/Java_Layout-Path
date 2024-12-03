package com.example.api;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 游家纨绔
 * @dateTime 2024-12-02 23:28
 * @apiNote TODO
 */
@Controller
public class UserController {

    @Autowired
    private RegisteredClientRepository registeredClientRepository;


    // TODO 创建客户端授权应用。修改配置的 spring.sql.init.mode=always ,启动项目后会在指定的数据库下生成库表
    //      启动后需要首先创建客户端授权应用，类似于 gitee、gihub 等第三方登录授权应用
    //      http://localhost:9090/create/app?clientName=yjwk&homePageUri=http%3A%2F%2F127.0.0.1%3A8080&redirectUri=http%3A%2F%2F127.0.0.1%3A8080%2Flogin%2Foauth2%2Fcode%2FmyClient&scopeSets=emalls,groups,pull_requests
    @RequestMapping("/create/app")
    public void createApp(@RequestParam String clientName,
                          @RequestParam String homePageUri,
                          @RequestParam String redirectUri,
                          @RequestParam List<String> scopeSets) {
        RegisteredClient registeredClient = RegisteredClient
                // 客户端唯一标识
                .withId(UUID.randomUUID().toString())
                // 客户端Id
                .clientId("yjwk")
                // 客户端密钥
                // .clientSecret("{bcrypt}" + new BCryptPasswordEncoder().encode("123456"))
                .clientSecret("123456")
                // 客户端名称
                .clientName(clientName)
                // 客户端身份的验证方法-clientAuthenticationMethods
                .clientAuthenticationMethods (methods ->
                        methods.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                )
                // 授权类型-authorizationGrantTypes
                .authorizationGrantTypes(types -> {
                    types.add(AuthorizationGrantType.AUTHORIZATION_CODE);
                    types.add(AuthorizationGrantType.REFRESH_TOKEN);
                })
                // 客户端应用首页-postLogoutRedirectUris
                .postLogoutRedirectUris(uris ->
                        uris.add(homePageUri)
                )
                // 客户端应用回调地址-客户端应用回调地址
                .redirectUris(uris-> uris.add(redirectUri))
                // 授权范围-scopes
                .scopes (scopes -> {
                    scopes.add("openid");
                    scopes.add("profile");
                    scopes.addAll(scopeSets);
                })
                // 客户端 settings-clientSettings
                .clientSettings(
                        ClientSettings.builder()
                        .requireAuthorizationConsent(true)  // 是否需要用户确认授权
                        .build()
                )
                .build();
        // 保存客户端授权应用
        registeredClientRepository.save(registeredClient);
    }
}
