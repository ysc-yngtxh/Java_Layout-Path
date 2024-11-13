package com.example.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-14 00:06
 * @apiNote TODO
 */
@Controller("/user")
public class UserController {

    @RequestMapping("/info")
    public @ResponseBody String index(HttpServletRequest request,
                                      @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                                      @AuthenticationPrincipal OAuth2User user) {
        request.setAttribute("userName", user.getName());
        request.setAttribute("clientName", client.getClientRegistration().getClientName());
        request.setAttribute("userAttrbutes", user.getAttributes());
        return "userinfo";
    }
}
