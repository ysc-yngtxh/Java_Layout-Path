package com.example.interceptor;

import com.example.exception.ApiException;
import com.example.annotation.BucketAnnotation;
import com.example.utils.BucketUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 令牌桶拦截器
 */
public class BucketInterceptor implements HandlerInterceptor {

    // 预处理回调方法，在接口调用之前使用  true代表放行  false代表不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 加有@BucketAnnotation注解的接口，才会被进行限流
        BucketAnnotation methodAnnotation = method.getAnnotation(BucketAnnotation.class);
        if (Objects.nonNull(methodAnnotation)) {
        	// 在名为：bucket的令牌桶里取令牌 取到即放行 未取到即抛出异常
            if(BucketUtil.buckets.get("bucket").getToken()) {
                return true;
            } else {
            	// 抛出自定义异常
                throw new ApiException("不好意思，您被限流了");
            }
        } else {
            return true;
        }
    }

    // 接口调用之后，返回之前 使用
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {}

    // 整个请求完成后，在视图渲染前使用
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {}
}
