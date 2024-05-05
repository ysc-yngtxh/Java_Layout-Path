package com.example.filter;

import com.example.context.Scope;
import com.example.utils.AuthScope;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Lazy
@Order(0)
@Component("scopeFilter")
public class ScopeFilter extends OncePerRequestFilter {

    @Override
    protected String getAlreadyFilteredAttributeName() {
        return this.getClass().getName();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request
                                  , HttpServletResponse response
                                  , FilterChain filterChain) throws ServletException, IOException {
        // 开启Scope
        Scope.beginScope();
        // 登录态通过Cookie模拟
        try {
            Cookie[] cookies = request.getCookies();
            String loginUser = "unknownUser";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("login_user")) {
                        loginUser = cookie.getValue();
                        break;
                    }
                }
            }

            // 设置该 Request 上下文中的登陆用户
            AuthScope.setLoginUser(loginUser);

            filterChain.doFilter(request, response);
        } finally {
            // 关闭Scope
            Scope.endScope();
        }
    }
}