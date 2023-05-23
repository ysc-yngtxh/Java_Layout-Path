package com.wen3.oauth.ss.authclient.processor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import java.io.IOException;

/**
 * @author tangheng
 */
public class OAuth2AuthorizationRequestRedirectFilterPostProcessor implements ObjectPostProcessor<OAuth2AuthorizationRequestRedirectFilter> {

    @Override
    public OAuth2AuthorizationRequestRedirectFilter postProcess(OAuth2AuthorizationRequestRedirectFilter object) {
        DefaultRedirectStrategy authorizationRedirectStrategy = new DefaultRedirectStrategy() {
            @Override
            public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
//                Cookie cookie = new Cookie("JSESSIONID", "");
//                cookie.setMaxAge(0);
//                cookie.setPath("/");
//                cookie.setHttpOnly(true);
//                response.addCookie(cookie);
                super.sendRedirect(request, response, url);
            }
        };
        object.setAuthorizationRedirectStrategy(authorizationRedirectStrategy);
        return object;
    }
}