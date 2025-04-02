package com.example.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OneFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request =(HttpServletRequest)servletRequest;
        HttpSession session = null;

        // 1、调用请求对象读取请求请求包中请求行中URI，了解用户访问的资源文件是谁
        String uri = request.getRequestURI();
        // 2、如果本次请求资源文件与登陆相关【login.html或者LoginServlet】,此时要无条件放行(因为此时的登录和提交页面进行过滤拦截，用户体验很差)
        if(uri.contains("login") || "/myWeb/".equals(uri)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        // 3、如果本次请求访问的是其他资源文件，需要得到用户在服务端HttpSession
        session = request.getSession(false);
        if(session != null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        // 4、做拒绝请求
        request.getRequestDispatcher("/login_error.html").forward(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {}
}
