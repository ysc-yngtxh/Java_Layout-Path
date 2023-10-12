package com.example.tenant;

import com.example.advice.SqlEnum;
import com.example.advice.SqlException;
import com.example.entity.TbUser;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-25 15:05
 * @apiNote TODO
 */
public class TenantContextHolderInterceptor implements HandlerInterceptor {
    @Override
    @SneakyThrows
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 这里是前端传给后端的请求头信息中必须要有 Global-TenantId,用来传递租户Id
        String tenant = request.getHeader("Global-TenantId");
        if (StringUtils.isBlank(tenant)) {
            throw new SqlException(SqlEnum.TENANT_NULL);
        }
        TenantContextHolder.setTbUser( TbUser.builder().tenantId(Integer.parseInt(tenant)).build() );
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 使用完 ThreadLocal 后要记得清理内存
        TenantContextHolder.clear();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
