package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 游家纨绔
 */
@WebFilter(urlPatterns = "/filter")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("===您已进入过滤器===");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
