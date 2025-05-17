package com.example.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.components.SpringContextHolder;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 * @dateTime 2023-07-11 07:00:00
 * @apiNote TODO 拦截器
 */
public class UserInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String authorization = request.getHeader("X-Token");
		if (StringUtils.hasText(authorization)) {
			// 获取该用户密码
			Claims claims = JwtUtil.parseJwt(null, authorization);
			String username = claims.get("username").toString();
			UserMapper userMapper = (UserMapper) SpringContextHolder.getApplicationContext().getBean("userMapper");
			// 校验密码
			String pwd = userMapper.selectOne(
					new LambdaQueryWrapper<User>().eq(User::getUserName, username)
			).getPassWord();
			boolean verifyPwd = claims.get("password").toString().equals(pwd);
			// 校验token是否过期
			int isExpire = claims.getExpiration().compareTo(new Date());
			return verifyPwd & isExpire>=0;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
