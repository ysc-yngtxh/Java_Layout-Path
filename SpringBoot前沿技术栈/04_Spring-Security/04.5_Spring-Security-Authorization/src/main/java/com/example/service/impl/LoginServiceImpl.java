package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.security.bo.LoginUserDetails;
import com.example.pojo.vo.ResponseResult;
import com.example.service.LoginService;
import com.example.utils.JwtUtil;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @description TODO 登录逻辑
 * @create 2023-04-16 下午12:10
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	public String login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 如果认证没通过，给出对应的提示
		if (ObjectUtil.isNull(authentication)) {
			throw new RuntimeException("登陆失败");
		}
		// 如果认证通过，根据我们的认证逻辑是获取该用户所有信息的
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();

		String userId = loginUserDetails.getUser().getId().toString();
		// 使用userId生成一个jwt，并将jwt放入ResponseResult返回
		return JwtUtil.createJwt(Map.of("id", userId));
	}

	public ResponseResult<String> logout() {
		return new ResponseResult<>(200, "登出成功", null);
	}
}
