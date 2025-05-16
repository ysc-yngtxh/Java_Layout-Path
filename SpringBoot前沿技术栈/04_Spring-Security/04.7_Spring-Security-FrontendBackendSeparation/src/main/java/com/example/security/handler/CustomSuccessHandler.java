package com.example.security.handler;

import com.alibaba.fastjson2.JSON;
import com.example.security.bo.LoginUserDetails;
import com.example.pojo.vo.ResponseResult;
import com.example.utils.RedisCache;
import com.example.utils.WebUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-15 11:40
 * @apiNote TODO 登录成功处理器
 */
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Resource
	private RedisCache redisCache;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// 认证通过，根据我们的认证逻辑是获取该用户所有信息的
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();

		String userId = loginUserDetails.getCurrentSysUserInfo().getId().toString();
		// 使用userId生成一个jwt，并将jwt放入ResponseResult返回
		String jwtToken = loginUserDetails.getCurrentSysUserInfo().getToken();
		// 把完整的用户信息存入Redis,使用userId作为key
		redisCache.setCacheObject("login:" + userId, loginUserDetails);
		ResponseResult<String> result = new ResponseResult<>(200, "登录成功！", jwtToken);
		WebUtil.renderText(response, JSON.toJSONString(result));
	}
}
