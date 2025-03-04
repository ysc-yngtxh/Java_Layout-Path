package com.example.filter;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.security.bo.LoginUserDetails;
import com.example.utils.JwtUtil;
import com.example.utils.MultiReadHttpServletRequest;
import com.example.utils.MultiReadHttpServletResponse;
import com.example.utils.RedisCache;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> 访问鉴权 - 每次访问接口都会经过此 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2019/10/12 16:17
 */
@Slf4j
@Component
public class MyAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("请求头类型： " + request.getContentType());
        // 这里我们做一下判断，如果是前后端一体的请求，
        if ((request.getContentType() == null && request.getContentLength() <= 0) || (request.getContentType() != null && !request.getContentType().contains("application/json"))) {
            filterChain.doFilter(request, response);
            return;
        }

        // 这里走的前后端分离的请求，请求数据类型为application/json
        MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
        MultiReadHttpServletResponse wrappedResponse = new MultiReadHttpServletResponse(response);
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            // 记录请求的消息体
            logRequestBody(wrappedRequest);

            // TODO 这里给需要忽略的路径放行

            // SecurityContext context = SecurityContextHolder.getContext();
            // 判断当前用户是否认证过,
            // if (context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
            //     filterChain.doFilter(wrappedRequest, wrappedResponse);
            //     return;
            // }

            // 前后端分离情况下，前端登录后将token储存在localStorage中，每次访问接口时通过token去拿用户权限
            String token = wrappedRequest.getHeader("token");
            log.debug("后台检查令牌:{}", token);
            if (StringUtils.isNotBlank(token)) {
                // 解析token
                String userId = null;
                try {
                    Claims claims = JwtUtil.parseJwt(token);
                    userId = claims.getSubject();
                } catch (Exception e) {
                    throw new RuntimeException("token非法");
                }
                // 从redis中获取用户信息
                String redisKey = "login:" + userId;
                Object cacheObject = redisCache.getCacheObject(redisKey);
                LoginUserDetails loginUserDetails =
                        JSONUtil.toBean(JSONObject.toJSONString(cacheObject), LoginUserDetails.class);

                if (loginUserDetails == null || loginUserDetails.getCurrentSysUserInfo() == null) {
                    throw new AccessDeniedException("TOKEN已过期，请重新登录！");
                }
                // 三个参数的构造方法，用以表明认证成功
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(loginUserDetails, loginUserDetails.getCurrentSysUserInfo().getPassword(), loginUserDetails.getAuthorities());
                // 全局注入角色权限信息和登录用户基本信息
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            stopWatch.stop();
            long usedTimes = stopWatch.getTotalTimeMillis();
            // 记录响应的消息体
            logResponseBody(wrappedRequest, wrappedResponse, usedTimes);
        }
    }

    public static Map<String, String> URL_MAPPING_MAP = new HashMap<>();

    private String logRequestBody(MultiReadHttpServletRequest request) {
        MultiReadHttpServletRequest wrapper = request;
        if (wrapper != null) {
            try {
                String bodyJson = wrapper.getJsonToJsonStr(request);
                String url = wrapper.getRequestURI().replace("//", "/");
                System.out.println("-------------------------------- 请求url: " + url + " --------------------------------");
                URL_MAPPING_MAP.put(url, url);
                log.info("`{}` 接收到的参数: {}", url, bodyJson);
                return bodyJson;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void logResponseBody(MultiReadHttpServletRequest request, MultiReadHttpServletResponse response, long useTime) {
        MultiReadHttpServletResponse wrapper = response;
        if (wrapper != null) {
            byte[] buf = wrapper.getBody();
            if (buf.length > 0) {
                String payload;
                try {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    payload = "[unknown]";
                }
                log.info("`{}`  耗时:{}ms  返回的参数: {}", URL_MAPPING_MAP.get(request.getRequestURI()), useTime, payload);
            }
        }
    }
}
