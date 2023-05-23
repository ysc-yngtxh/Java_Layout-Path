package com.wen3.oauth.ss.authclient.processor;

import com.wen3.oauth.ss.authclient.handler.DemoAuthenticationSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.DefaultRedirectStrategy;

import java.io.IOException;

/**
 * @author tangheng
 */
public class OAuth2LoginAuthenticationFilterPostProcessor implements ObjectPostProcessor<OAuth2LoginAuthenticationFilter> {

    @Override
    public OAuth2LoginAuthenticationFilter postProcess(OAuth2LoginAuthenticationFilter object) {
        AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository = new HttpSessionOAuth2AuthorizationRequestRepository();
        object.setAuthorizationRequestRepository(authorizationRequestRepository);
        object.setAuthenticationSuccessHandler(new DemoAuthenticationSuccessHandler());
        return object;
    }
}