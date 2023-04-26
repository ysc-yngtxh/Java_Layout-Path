package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.domain.LoginUser;
import com.example.domain.ResponseResult;
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


    public String login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 如果认证没通过，给出对应的提示
        if (ObjectUtil.isNull(authentication)) {
            throw new RuntimeException("登陆失败");
        }
        // 如果认证通过，根据我们的认证逻辑是获取该用户所有信息的
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        String userId = loginUser.getUser().getId().toString();
        return userId;
    }

    public ResponseResult<Void> logout() {
        // 获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        return new ResponseResult<>(200, "登出成功", null);
    }
}
