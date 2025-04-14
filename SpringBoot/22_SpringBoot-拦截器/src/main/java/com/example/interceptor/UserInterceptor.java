package com.example.interceptor;

import com.example.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;

/**
 * @author 游家纨绔
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("===springboot拦截器启动===");

        // 如果是SpringMVC请求
        // 常用的 @RequestMapping 标注的方法会对应的 handler 类型为 HandlerMethod
        if(HandlerMethod.class.equals(handler.getClass())) {  // handler instanceof HandlerMethod
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            log.info("当前拦截的方法为：{}", handlerMethod.getMethod().getName());
            log.info("当前拦截的方法参数长度为：{}", handlerMethod.getMethod().getParameters().length);
            log.info("当前拦截的方法为：{}", handlerMethod.getBean().getClass().getName());
            System.out.println("开始拦截---------");
            String uri = request.getRequestURI();
            System.out.println("拦截的uri："+uri);
        }

        // 编写业务拦截的规则，从session中获取用户的信息
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
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {}
}
