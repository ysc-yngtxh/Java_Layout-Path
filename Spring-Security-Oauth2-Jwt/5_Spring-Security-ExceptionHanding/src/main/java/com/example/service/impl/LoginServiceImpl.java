package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.domain.LoginUser;
import com.example.domain.ResponseResult;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author youshicheng
 * @description TODO 登录逻辑
 * @create 2023-04-16 下午12:14
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl {

    private final RedisCache redisCache;

    public String login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 如果认证没通过，给出对应的提示
        if (ObjectUtil.isNull(authentication)) {
            throw new RuntimeException("登陆失败");
        }
        // 如果认证通过，根据我们的认证逻辑是获取该用户所有信息的
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        String userId = loginUser.getCurrentUserInfo().getId().toString();
        // 使用userId生成一个jwt，并将jwt放入ResponseResult返回
        String jwt = loginUser.getCurrentUserInfo().getToken();
        // 把完整的用户信息存入Redis,使用userId作为key
        redisCache.setCacheObject("login:" + userId, loginUser);
        return jwt;
    }

    public ResponseResult<Void> logout() {
        // 获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getCurrentUserInfo().getId().toString();
        // 删除redis中的值
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult<>(200, "登出成功", null);
    }
}
