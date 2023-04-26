package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.domain.LoginUser;
import com.example.domain.ResponseResult;
import com.example.pojo.User;
import com.example.service.LoginService;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.context.request.RequestAttributes.REFERENCE_REQUEST;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/07/30 23:46
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    private final RedisCache redisCache;

    @Override
    public Map<Object, Object> login() {
        System.out.println("登录认证执行标记==================");
        // 用户信息包装
        // UsernamePasswordAuthenticationToken authenticationToken =
        //         new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        // UserDetails是security提供的一个接口，一般实体类的用户需要去实现它，
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();

        // 进行认证
        // Authentication authenticate = authenticationManager.authenticate(token);

        // 如果认证没通过，给出对应的提示
        if (ObjectUtil.isNull(authenticationToken)) {
            throw new RuntimeException("登陆失败");
        }
        // 如果认证通过，根据我们的认证逻辑是获取该用户所有信息的
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        // 使用userId生成一个jwt，并将jwt放入ResponseResult返回
        String jwt = JwtUtil.createJwt(userId);
        Map<Object, Object> map = new HashMap<>();
        map.put("token", jwt);
        // 把完整的用户信息存入Redis,使用userId作为key
        redisCache.setCacheObject("login:" + userId, loginUser);

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        // 获取Session中参数
        requestAttributes.setAttribute("token", map.get("token"), SCOPE_REQUEST);
        return map;
    }

    @Override
    public ResponseResult<Void> logout() {
        // 获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        // 删除redis中的值
        redisCache.deleteObject("login:" + userId);
        return new ResponseResult<>(200, "登出成功", null);
    }
}
