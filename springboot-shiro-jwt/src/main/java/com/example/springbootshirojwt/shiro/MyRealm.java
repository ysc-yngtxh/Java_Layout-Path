package com.example.springbootshirojwt.shiro;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.springbootshirojwt.pojo.User;
import com.example.springbootshirojwt.service.ShiroJwtService;
import com.example.springbootshirojwt.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;


public class MyRealm extends AuthorizingRealm {

    @Autowired
    private ShiroJwtService shiroJwtService;

    //限定这个realm只能处理JwtToken（不加的话会报错）
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行了--doGetAuthorizationInfo授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        String username = JwtUtils.getUsername(principalCollection.getPrimaryPrincipal().toString());

        // 根据登录名获取登用户信息
        if (!StringUtils.isEmpty(username)) {

            User user = shiroJwtService.queryByName(username);
            //设置角色权限
            info.addRole(user.getPerms());
            
            //info.addRole("vip");
            //设置权限
            //info.addStringPermission("user:vip");
        }

        return info;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {

        System.out.println("执行了--doGetAuthenticationInfo认证"+auth.toString());

        //获取token
        String principal = (String)auth.getCredentials();
        System.out.println(principal);
        String username = JwtUtils.getUsername(principal);
        System.out.println(username);

        if (StringUtils.isEmpty(username)) {
            throw new AuthenticationException("token错误!");
        }

        //根据用户名，查询数据库获取到正确的用户信息
        User user = shiroJwtService.queryByName(username);
        if (user == null) {
            throw new AuthenticationException("用户不存在!");
        }

        try {
            if (JwtUtils.verifyToken(principal, user.getPwd())) {
                return new SimpleAuthenticationInfo(principal, principal, getName());
            } else {
                throw new AuthenticationException("token认证失败!");
            }
        } catch (TokenExpiredException e) {
            throw new AuthenticationException("token已过期!");
        } catch (SignatureVerificationException e) {
            throw new AuthenticationException("密码不正确!");
        }
    }
}
