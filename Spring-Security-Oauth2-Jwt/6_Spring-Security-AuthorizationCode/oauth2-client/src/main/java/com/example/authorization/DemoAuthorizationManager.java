package com.wen3.oauth.ss.authclient.authorization;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * @author tangheng
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class DemoAuthorizationManager<T> implements AuthorizationManager<T> {

    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    private final HttpServletRequest httpServletRequest;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> supplier, T object) {
        Authentication authentication = supplier.get();
        log.info("authentication: {}", authentication);
        log.info("object: {}", object);
        boolean isAnonymous = authentication != null && !this.trustResolver.isAnonymous(authentication)
                && authentication.isAuthenticated();

        if(!isAnonymous) {
            return new AuthorizationDecision(false);
        }

        String servletPath = httpServletRequest.getServletPath();
        log.info("servletPath: {}", servletPath);
        // TODO: 判断当前用户是否拥有访问servletPath的权限
        boolean granted = true;

        return new AuthorizationDecision(granted);
    }
}