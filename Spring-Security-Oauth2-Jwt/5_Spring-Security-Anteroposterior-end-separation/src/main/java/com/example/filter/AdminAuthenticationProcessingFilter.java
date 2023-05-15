package com.example.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.authentication.CustomAuthenticationManager;
import com.example.entity.SysUser;
import com.example.utils.MultiReadHttpServletRequest;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> 自定义用户密码校验过滤器 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2023/05/12 15:32
 */
@Slf4j
@Component
public class AdminAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    protected AdminAuthenticationProcessingFilter(CustomAuthenticationManager customAuthenticationManager) {
        super(new AntPathRequestMatcher("/toLogin", "POST"));
        this.setAuthenticationManager(customAuthenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // TODO 这里需要注意：SpringSecurity不支持JSON的数据类型进行认证。但是现在大都是前后端分离的项目。
        //                 所以只有当你的认证用户数据是JSON类型的，才会进入到这里尝试认证。
        //                 否则，就是进入到 UsernamePasswordAuthenticationFilter 中的 attemptAuthentication() 方法
        if (request.getContentType() == null || !request.getContentType().contains("application/json")) {
            throw new AuthenticationServiceException("请求头类型不支持: " + request.getContentType());
        }

        UsernamePasswordAuthenticationToken authRequest;
        try {
            MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
            // 将前端传递的数据转换成jsonBean数据格式
            SysUser sysUser = JSONObject.parseObject(wrappedRequest.getJsonToJsonStr(wrappedRequest), SysUser.class);
            authRequest = new UsernamePasswordAuthenticationToken(sysUser.getUserName(), sysUser.getPassword(), null);

            // 提供子类可以配置放入身份验证请求的详细信息属性的内容。
            // 参数：wrappedRequest – 正在为其创建身份验证请求
            //      authRequest – 应该设置其详细信息的身份验证请求对象
            authRequest.setDetails(authenticationDetailsSource.buildDetails(wrappedRequest));
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
