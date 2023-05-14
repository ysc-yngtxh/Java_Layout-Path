package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.domain.LoginUser;
import com.example.domain.ResponseResult;
import com.example.service.LoginService;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * @author youshicheng
 * @description TODO 登录逻辑
 * @create 2023-04-16 下午12:14
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {


    public String login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 如果认证没通过，给出对应的提示
        if (ObjectUtil.isNull(authentication)) {
            throw new RuntimeException("登陆失败");
        }
        // 如果认证通过，根据我们的认证逻辑是获取该用户所有信息的
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        String userId = loginUser.getUser().getId().toString();
        // 使用userId生成一个jwt，并将jwt放入ResponseResult返回
        return JwtUtil.createJwt(userId);
    }

    public ResponseResult<String> logout() {
        return new ResponseResult<>(200, "登出成功", null);
    }
}
