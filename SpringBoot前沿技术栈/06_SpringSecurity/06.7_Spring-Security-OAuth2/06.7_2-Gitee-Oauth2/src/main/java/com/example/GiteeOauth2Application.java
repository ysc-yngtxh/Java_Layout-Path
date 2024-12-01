package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@MapperScan("com.example.mapper")
// 启用SpringSecurity的权限注解。启用@EnableMethodSecurity注解后，prePostEnabled属性值默认为 true。
// prePostEnabled = true：启用 @PreAuthorize 和 @PostAuthorize 注解。
// securedEnabled = true：启用 @Secured 注解。
// jsr250Enabled = true：启用 JSR-250 注解（如 @RolesAllowed）。
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class GiteeOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(GiteeOauth2Application.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "forward:/login";
    }


    @RequestMapping("/oauth2/gitee")
    public @ResponseBody String GiteeAuthCallback() {
        return "Hello World！认证成功！！！";
    }

    // OAuth2 聚焦于资源的共享，只要持有正确的访问令牌就可向资源服务器请求到资源。
    // 换句话说，资源服务器根本不关心"用户"是谁，只关心这个令牌可以访问什么。因此就需要一个协议来关注访问的“用户”。

    // CIDC是 OpenID Connect 的简写，它是由 OpenID 基金会开发的、以 OAuth2.0 为基础一种身份层协议。
    // OIDC 则既处理授权也处理认证，它确保应用不仅可以获取访问权限，还能确认用户的真实身份。
    // OIDC 引入了 OpenId 的概念，这是一种包含用户身份信息的 Jwt，使得应用可以确认是“谁”在访问。

    // OIDC 是基于 OAuth2.0 的，因此 OAuth2.0 的所有特性 OIDC 都有，并且还扩展了以下特性：
    // 1. OIDC 提供了身份认证的功能，而 OAuth2.0 只提供了授权的功能。
    // 2. OIDC 的 token 是 JWT 格式，而 OAuth2.0 的 token 是字符串格式。
    // 3. OIDC 的 token 包含了用户的身份信息，而 OAuth2.0 的 token 不包含用户的身份信息。
    // 4. OIDC 的 token 可以被验证、刷新、撤销，而 OAuth2.0 的 token 不能被验证、刷新、撤销。

}
