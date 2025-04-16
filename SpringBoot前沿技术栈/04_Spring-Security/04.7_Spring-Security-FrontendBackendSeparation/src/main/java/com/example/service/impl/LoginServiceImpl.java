package com.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.security.bo.LoginUserDetails;
import com.example.pojo.vo.ResponseResult;
import com.example.pojo.entity.SysUser;
import com.example.mapper.SysUserMapper;
import com.example.service.LoginService;
import com.example.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author example
 * @description TODO 登录逻辑
 * @create 2023-04-16 下午12:14
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final RedisCache redisCache;

    private final SysUserMapper sysUserMapper;

    public SysUser findByUser(String userName) {
        return sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUserName, userName));
    }

    public ResponseResult<String> logout() {
        // 获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        String userId = loginUserDetails.getCurrentSysUserInfo().getId().toString();
        // 删除redis中的值
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult<>(200, "登出成功", null);
    }
}
