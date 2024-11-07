package com.example.api;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.constant.Oauth2Property;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-03 09:01
 * @apiNote TODO
 */
@Controller
public class GiteeOath2Controller {

    // TODO https://api.gitee.com/api/v5/oauth_doc#/
    /**
     * gitee 请求授权页面
     */
    @RequestMapping("/gitee/auth")
    public String giteeAuth() {
        // TODO Step1：获取Authorization Code
        StringBuilder urlBuilder = new StringBuilder(Oauth2Property.AUTHORIZE_URL);
        urlBuilder.append("?response_type=code")
                .append("&client_id=").append(Oauth2Property.CLIENT_ID)
                .append("&redirect_uri=").append(URLEncoder.encode(Oauth2Property.CALLBACK_URI))
                .append("&scope=user_info");

        // 重定向
        return "redirect:" + urlBuilder;
    }

    /**
     * 授权回调
     * @param code 授权编码
     */
    @RequestMapping("/gitee/callback")
    public @ResponseBody String AuthCallback(@RequestParam("code") String code) {
        try {
            // 得到Authorization Code
            System.out.println("授权服务器的Authorization code = " + code);

            // TODO Step2：通过Authorization Code获取Access Token
            Map<String, Object> params = new HashMap<>();
            params.put("grant_type", "authorization_code");
            params.put("client_id", Oauth2Property.CLIENT_ID);
            params.put("client_secret", Oauth2Property.CLIENT_SECRET);
            params.put("code", code);
            params.put("redirect_uri", Oauth2Property.CALLBACK_URI);

            String authRequest = HttpRequest.post(Oauth2Property.TOKEN_URL)
                    .form(params)      // 表单内容
                    .timeout(20000)  // 超时，毫秒
                    .execute().body();

            JSONObject accessTokenJson = Optional.ofNullable(authRequest).map(JSONUtil::parseObj).get();
            System.out.println("accessTokenJson = " + accessTokenJson);
            if (!accessTokenJson.containsKey("access_token")) {
                throw new RuntimeException("获取accessToken失败");
            }

            // 授权服务器提供的访问Token
            String accessTokenStr = accessTokenJson.get("access_token").toString();

            // TODO Step3: 获取用户信息
            HttpRequest httpRequest = HttpRequest.get(Oauth2Property.GET_USER_INFO_URL + "?access_token=" + accessTokenStr);
            System.out.println("httpRequest = " + httpRequest);
            return httpRequest.execute().body();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
