package com.example.interceptor;

import com.example.common.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-01 11:48
 * @apiNote TODO 测试拦截器
 */
public class TestInterceptor implements HandlerInterceptor {
    public final Logger log = LoggerFactory.getLogger(TestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getContentType() != null && request.getContentType().contains(Constants.REQUEST_CONTEXT_TYPE)) {
            System.out.println("================================进入拦截器==============================");
            // 该数据类型并不能通过getParameter()获取参数值
            String userName = request.getParameter("userName");
            String s = new String(StreamUtils.copyToByteArray(request.getInputStream()));
            log.info("参数{},username值为{}", s, userName);
        }
        return true;
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
