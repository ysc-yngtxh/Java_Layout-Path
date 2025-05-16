package com.example.api;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-14 00:00
 * @apiNote TODO 用户登录后即可访问的接口
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/info")
	public String index(HttpServletRequest request,
	                    @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
	                    @AuthenticationPrincipal OAuth2User user) {
		request.setAttribute("userName", user.getName());
		request.setAttribute("userAttributes", user.getAttributes());
		request.setAttribute("clientName", client.getClientRegistration().getClientName());
		return "userinfo";
	}

	@RequestMapping("/context")
	public @ResponseBody Principal context(Principal principal) {
		return principal;
	}
}
