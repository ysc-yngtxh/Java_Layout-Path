package com.example.interceptor;

import com.example.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 游家纨绔
 */
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("===springboot拦截器启动===");

        // 编写业务拦截的规则
        // 从session中获取用户的信息
        User user = (User) request.getSession().getAttribute("user");

        // 判断用户是否登录
        if(null == user){
            // 未登录
            response.sendRedirect(request.getContextPath() + "/user/error");
            // sendRedirect重定向；forward请求转发
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
