package com.example.constant;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-06 14:16
 * @apiNote TODO gitee授权信息
 */
public interface Oauth2Property {

    /**
     * gitee授权信息
     */
    // 客户端 Id
    public String CLIENT_ID = "8def619da68a212d02a36d471cef229ab3b80c81222e76ed2e581de76f9a6d0a";
    // 客户端密钥
    public String CLIENT_SECRET = "1f5276f0abd9c8b8b5eee50356ca912d61e62a59d0b8ad285dc49b4879b0ddad";
    // 授权路径
    public String AUTHORIZE_URL = "https://gitee.com/oauth/authorize";
    // 回调路径
    public String CALLBACK_URI = "http://localhost:8080/login/oauth2/code/gitee";
    // 令牌路径
    public String TOKEN_URL = "https://gitee.com/oauth/token";
    // 获取用户信息路径
    public String GET_USER_INFO_URL = "https://gitee.com/api/v5/user";

    /**
     * github授权信息
     */
    // 客户端 Id
    public String GITHUB_CLIENT_ID = "Ov23liukOOM7knKdMlD8";
    // 客户端密钥
    public String GITHUB_CLIENT_SECRET = "5f6ec1ae0581fbaed813b2cf2c643736ad4e4cb5";
    // 授权路径
    public String GITHUB_AUTHORIZE_URL = "https://github.com/login/oauth/authorize";
    // 回调路径
    public String GITHUB_CALLBACK_URI = "http://localhost:8080/login/oauth2/code/github";
    // 令牌路径
    public String GITHUB_TOKEN_URL = "https://github.com/login/oauth/access_token";
    // 获取用户信息路径
    public String GITHUB_GET_USER_INFO_URL = "https://api.github.com/user";

}
