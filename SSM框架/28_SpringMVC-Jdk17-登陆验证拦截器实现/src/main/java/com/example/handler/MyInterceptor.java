package com.example.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 游家纨绔
 * @Desc 拦截器类：拦截用户的请求
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("拦截器的MyInterceptor的preHandle()");

        String loginName = "";
        // 从session中获取name的值
        Object attr = request.getSession().getAttribute("name");
        if(attr != null) {
            loginName = (String) attr;
        }
        // 判断登陆的账户，是否符合要求
        if(!"zs".equals(loginName)) {
            request.getRequestDispatcher("/tip.jsp").forward(request, response);
            return false;
        }
        return true;
    }
}
