package com.example.springbootshirojwt.filter;

import com.alibaba.fastjson.JSON;
import com.example.springbootshirojwt.vo.BackResponse;
import com.example.springbootshirojwt.shiro.JwtToken;
import com.example.springbootshirojwt.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*自定义一个Filter，用来拦截所有的请求判断是否携带Token
  这个过滤器并没有注入到容器中，所以只有在被调用时候才会起作用
  isAccessAllowed()判断是否携带了有效的JwtToken
  onAccessDenied()是没有携带JwtToken的时候进行账号密码登录，登录成功允许访问，登录失败拒绝访问*/
@Slf4j
public class JwtFilter extends AuthenticationFilter {

    public void print(HttpServletResponse res,BackResponse back) {
        PrintWriter out = null;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json; charset=utf-8");
            out = res.getWriter();
            out.print(JSON.toJSONString(back));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /*1. 返回true，为登录状态
      2. 返回false，shiro才会调用onAccessDenied的方法*/
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.warn("isAccessAllowed 方法被调用");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String jwt = req.getHeader("Authorization");
        System.out.println(JwtUtils.getUsername(jwt));
        BackResponse back = new BackResponse();
        JwtToken token = new JwtToken(jwt);

        // 请求头中有token，那么就去Authentication中去认证，以及后续的授权操作
        if ( !StringUtils.isEmpty(jwt) ) {
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                back.error(401, "认证授权错误！");
                print(res,back);
                e.printStackTrace();
                return false;
            }
        }

        log.info("请求的 Header 中藏有 jwtToken {" + jwt + "}");

        // 走到这里就是不存在token，但还是返回turn，因为可能是游客访问及用户登录，所以放行请求。反正没有走认证授权，就算是恶意访问，它也没有权限。
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }


    //判断是否拒绝登录
    /*@Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        log.warn("onAccessDenied 方法被调用");//所以以后发起请求的时候就需要在Header中放一个Authorization，值就是对应的Token

        return true;
    }*/

}
