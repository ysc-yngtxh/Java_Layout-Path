package com.example.自定义注解.interceptor;

import com.example.自定义注解.annotation.LoginRequired;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/12/1 13:32
 */
@SuppressWarnings("NullableProblems")
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 常用的 @RequestMapping 标注的方法会对应的 handler 类型为 HandlerMethod
        if(HandlerMethod.class.equals(handler.getClass())) {  // handler instanceof HandlerMethod
            HandlerMethod handl = (HandlerMethod) handler;
            LoginRequired annotation = handl.getMethod().getAnnotation(LoginRequired.class);
            String str = ResourceBundle.getBundle("infoClass").getString("white.open");
            if (Objects.nonNull(annotation)) {
                // 获取注解上的值
                String value = annotation.value();
                if (Arrays.asList(str.split("-")).contains(value)) {
                    return true;
                }
            }
        }

        // 登录session
        Object login = request.getSession().getAttribute("login");
        if (Objects.nonNull(login)) {
            return true;
        }
        response.setContentType("text/html; charset=UTF-8");
        // response.setCharacterEncoding("utf-8"); 使用这个还是乱码
        response.getWriter().print("没登陆，请先登录再访问!!!");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
