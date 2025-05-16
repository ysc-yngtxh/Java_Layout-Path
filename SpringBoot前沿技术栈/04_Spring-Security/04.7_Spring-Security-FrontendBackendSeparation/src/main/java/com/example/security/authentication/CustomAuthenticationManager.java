package com.example.security.authentication;

import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-12 19:50
 * @apiNote TODO 自定义认证管理器
 */
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

	@Resource
	private CustomAuthenticationProvider authenticationProvider;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication result = authenticationProvider.authenticate(authentication);
		if (Objects.nonNull(result)) {
			return result;
		}
		throw new ProviderNotFoundException("Authentication failed!");
	}
}
