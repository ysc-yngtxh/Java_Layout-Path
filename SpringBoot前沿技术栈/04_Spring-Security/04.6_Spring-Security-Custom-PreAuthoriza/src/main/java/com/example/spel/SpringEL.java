package com.example.spel;

import com.example.security.bo.LoginUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 游家纨绔
 * @date 2023-04-17 下午4:20:00
 * @apiNote TODO SpEL语法表达式
 */
@Component("spel")
public class SpringEL {

	public boolean hasAuthorization() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginUserDetails user = (LoginUserDetails) authentication.getAuthorities();
		List<String> permission = user.getPermission();
		// 匹配 'system:*:*'
		Pattern compile = Pattern.compile("^(system:)?([a-z]+:)?([a-z]+)$");
		// 只要有任一权限满足正则匹配，即可访问
		return permission.stream().anyMatch(item -> compile.matcher(item).matches());
	}

}
