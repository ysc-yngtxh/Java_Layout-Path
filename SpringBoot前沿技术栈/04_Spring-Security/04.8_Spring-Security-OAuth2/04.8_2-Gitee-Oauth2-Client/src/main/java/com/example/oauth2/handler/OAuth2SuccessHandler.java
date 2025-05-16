package com.example.oauth2.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-29 12:40
 * @apiNote TODO OAuth2 登录成功处理
 */
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    Authentication authentication) {
		// 自定义成功处理逻辑
		try {
			super.onAuthenticationSuccess(request, response, authentication);
		} catch (IOException | ServletException e) {
			throw new RuntimeException(e);
		}
	}
}
