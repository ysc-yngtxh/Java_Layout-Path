package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SSOAuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOAuthorizationServerApplication.class, args);
    }

    // 基于SAS+OAuth2实现单点登录（登出）
    // 1、什么是单点登录
    //    单点登录，英文叫做Single Sign On，简称SSO。
    //    SSO允许用户在一个应用系统群中的某一个应用里登录之后，能够访问多个相互信任的其他应用系统而无需再次进行登录。
    //    例如：哔哩哔哩的应用系统群
    //         1️⃣. 主站: https://www.bilibili.com/
    //         2️⃣. 会员购: https://show.bilibili.com/
    //         3️⃣. 漫画: https://manga.bilibili.com/

    // 2、SSO认证中心
    //    相比于单应用系统的登录，SSO需要一个独立的认证中心。
    //    这意味着，一旦用户在SSO 系统上成功登录，其身份验证信息（如session和cookie）将被共享给所有子系统，
    //    使得用户无需在每个子系统上再次进行登录。

}
