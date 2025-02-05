package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class OIDCAuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OIDCAuthorizationServerApplication.class, args);
    }

    // UserInfo 端点是在 OIDC 协议中定义的、用于安全暴露用户数据给客户端的接口，
    // 但是，当时所开发的客户端应用是基于 Gitee 授权服务器的，而 Gitee 采用的是标准OAuth2协议，
    // 所以客户端可以直接携带 access_token 请求用户信息，但这种方式并不适用于SpringAuthorization Server。

}
