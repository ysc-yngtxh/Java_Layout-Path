package com.example.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 游家纨绔
 */
// 拦截器类：拦截用户的请求
public class MyInterceptor2 implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("拦截器的MyInterceptor2的preHandle()");
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
        System.out.println("拦截器的MyInterceptor2的postHandle()");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("拦截器的MyInterceptor2的afterCompletion()");
    }
}
