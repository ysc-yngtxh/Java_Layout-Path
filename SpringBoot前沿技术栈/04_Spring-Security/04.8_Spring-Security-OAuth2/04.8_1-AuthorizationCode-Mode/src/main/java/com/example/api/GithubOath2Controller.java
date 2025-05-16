package com.example.api;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpRequest;
import com.example.constant.Oauth2Property;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-03 09:00
 * @apiNote TODO
 */
@Controller
public class GithubOath2Controller {

	// TODO https://docs.github.com/zh/apps/oauth-apps/building-oauth-apps/authorizing-oauth-apps

	/**
	 * github 请求授权页面 CommonOAuth2Provider
	 */
	@RequestMapping("/github/auth")
	public String githubAuth() {
		// TODO Step1：获取Authorization Code
		StringBuilder urlBuilder = new StringBuilder(Oauth2Property.GITHUB_AUTHORIZE_URL);
		urlBuilder.append("?response_type=").append("code")
		          .append("&client_id=").append(Oauth2Property.GITHUB_CLIENT_ID)
		          .append("&scope=").append("repo%20user")
		          .append("&state=").append(UUID.randomUUID())
		          .append("&redirect_uri=").append(URLEncoder.encode(Oauth2Property.GITHUB_CALLBACK_URI, StandardCharsets.UTF_8));

		// 重定向
		return "redirect:" + urlBuilder;
	}

	/**
	 * 授权回调
	 *
	 * @param code 授权编码
	 */
	@RequestMapping("/login/oauth2/code/github")
	public @ResponseBody String AuthCallback(@RequestParam("code") String code) {
		try {
			// 得到Authorization Code
			System.out.println("授权服务器的Authorization code = " + code);

			// TODO Step2：通过Authorization Code获取Access Token
			Map<String, Object> params = new HashMap<>();
			params.put("code", code);
			params.put("client_id", Oauth2Property.GITHUB_CLIENT_ID);
			params.put("client_secret", Oauth2Property.GITHUB_CLIENT_SECRET);
			params.put("redirect_uri", Oauth2Property.GITHUB_CALLBACK_URI);

			String authRequest = HttpRequest.post(Oauth2Property.GITHUB_TOKEN_URL)
			                                .form(params)      // 表单内容
			                                .timeout(20000)  // 超时，毫秒
			                                .execute().body();

			String[] resParam = authRequest.split("&");
			Map<String, Object> map = new HashMap<>();
			Arrays.stream(resParam).forEach(item -> {
				String[] split = item.split("=", 2);
				if (split.length == 2) {
					map.put(split[0], split[1]);
				}
			});
			System.out.println("accessTokenJson = " + map);
			if (!map.containsKey("access_token")) {
				throw new RuntimeException("获取accessToken失败");
			}

			// 授权服务器提供的访问Token
			String accessTokenStr = map.get("access_token").toString();

			// TODO Step3: 获取用户信息
			HttpRequest httpRequest = HttpRequest.get(Oauth2Property.GITHUB_GET_USER_INFO_URL);
			// Bearer：是一种特定类型的 Token，遵循 OAuth 2.0 规范，用于表示持有者可以访问受保护资源。
			//         如果您正在使用 OAuth 2.0 认证流程，推荐使用 Bearer。
			// Token：是一个更通用的术语，可以指代任何类型的认证令牌。
			//        如果您有自定义的认证机制或使用其他标准（如 JWT），可以选择使用 Token 或其他自定义格式。
			httpRequest.header("Authorization", "Bearer " + accessTokenStr);
			// httpRequest.header("Authorization", "token " + accessTokenStr);
			System.out.println("httpRequest = " + httpRequest);
			return httpRequest.execute().body();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
