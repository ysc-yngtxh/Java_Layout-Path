package com.wen3.oauth.ss.authclient.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangheng
 */
@Slf4j
@RestController
public class OauthClientDemoController {

    @RequestMapping(path = "/hello")
    public Object demo(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);
//        OAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();

        Cookie cookie = new Cookie("c_name", RandomStringUtils.randomAlphabetic(10));
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);

        return authentication.getPrincipal();
    }

    @RequestMapping(path = "/{action}/oauth2/code/{registrationId}")
    public String callback1(@PathVariable("action") String action,
                            @PathVariable("registrationId") String registrationId) {
        return action+",hello " + registrationId;
    }

    @GetMapping(value = "/authorize", params = "grant_type=authorization_code")
    public String authorizationCodeGrant(
            @RegisteredOAuth2AuthorizedClient("client-id-2")
            OAuth2AuthorizedClient authorizedClient) {

        return "hello authorizationCodeGrant";
    }

    @GetMapping(value = "/authorize", params = "grant_type=client_credentials")
    public String clientCredentialsGrant() {
        return "hello clientCredentialsGrant";
    }

    @RequestMapping(value = "/oauth2/authorization/{registrationId}")
    public void loginCallback(HttpServletRequest request, String registrationId) {
        request.getRequestDispatcher("/");
    }
}