package com.example.utils;

import com.example.context.ScopeKey;

// 获取登录态的工具类
public class AuthScope {

	private static final ScopeKey<String> LOGIN_USER = new ScopeKey<>();

	public static String getLoginUser() {
		return LOGIN_USER.get();
	}

	public static void setLoginUser(String loginUser) {
		if (loginUser == null) {
			loginUser = "unknownUser";
		}
		LOGIN_USER.set(loginUser);
	}
}
