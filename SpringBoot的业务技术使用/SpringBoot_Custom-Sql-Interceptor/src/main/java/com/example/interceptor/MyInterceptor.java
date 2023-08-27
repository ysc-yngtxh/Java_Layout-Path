package com.example.interceptor;

import com.example.advice.SqlEnum;
import com.example.advice.SqlException;
import com.example.annotation.ExcludeTenant;
import com.example.holder.TenantContextHolder;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-25 15:05
 * @apiNote TODO
 */
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 这里是前端传给后端的请求头信息中必须要有 Global-TenantId,用来传递租户Id
        String tenant = request.getHeader("Global-TenantId");
        if(StringUtils.isBlank(tenant)){
            throw new SqlException(SqlEnum.TENANT_NULL);
        }else{
            TenantContextHolder.setTenant(tenant);
            return true;
        }
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
