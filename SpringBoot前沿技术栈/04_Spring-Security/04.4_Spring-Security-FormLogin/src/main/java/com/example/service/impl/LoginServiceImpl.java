package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.security.bo.LoginUserDetails;
import com.example.service.LoginService;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2022-07-30 23:40:00
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final RedisCache redisCache;

	@Override
	public Map<String, Object> login() {
		System.out.println("登录认证执行标记==================");
		// 用户信息包装
		// UsernamePasswordAuthenticationToken authenticationToken =
		//         new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

		// UserDetails是SpringSecurity提供的一个接口，一般实体类的用户需要去实现它，
		Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();

		// 进行认证
		// Authentication authenticate = authenticationManager.authenticate(token);

		// 如果认证没通过，给出对应的提示
		if (ObjectUtil.isNull(authenticationToken)) {
			throw new RuntimeException("登陆失败");
		}
		// 如果认证通过，根据我们的认证逻辑是获取该用户所有信息的
		// principal   参数代表认证的主体信息，通常为用户名或用户对象；
		// credentials 参数代表认证的凭证信息，通常为密码或其他类似信息。
		LoginUserDetails loginUserDetails = (LoginUserDetails) authenticationToken.getPrincipal();
		String userId = loginUserDetails.getUser().getId().toString();
		// 使用userId生成一个jwt，并将jwt放入ResponseResult返回
		String jwt = JwtUtil.createJwt(userId);
		Map<String, Object> map = new HashMap<>();
		map.put("token", jwt);
		// 把完整的用户信息存入Redis,使用userId作为key
		redisCache.setCacheObject("login:" + userId, loginUserDetails);

		// 获取请求中的属性对象
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		// 获取Session中参数
		requestAttributes.setAttribute("token", map.get("token"), SCOPE_REQUEST);
		return map;
	}
}
