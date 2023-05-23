package com.wen3.oauth.ss.resourceserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tangheng
 */
@RestController
public class UserController {

    @RequestMapping(path = "/user/info", method = {RequestMethod.GET,RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AbstractOAuth2TokenAuthenticationToken<OAuth2Token> token = (AbstractOAuth2TokenAuthenticationToken<OAuth2Token>)authentication;
        List<String> list = (List<String>)token.getTokenAttributes().get("scope");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "xxx");
        if(list.contains("phone")) {
            map.put("phone", "18888888888");
        }
        if(list.contains("email")) {
            map.put("email", "xxx@xxx.com");
        }
        return map;
    }
}