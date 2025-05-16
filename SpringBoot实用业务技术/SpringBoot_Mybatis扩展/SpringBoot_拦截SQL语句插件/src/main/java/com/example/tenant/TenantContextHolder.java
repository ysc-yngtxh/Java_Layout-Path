package com.example.tenant;

import com.example.entity.User;

public class TenantContextHolder {

	private static final ThreadLocal<User> CONTEXT = new ThreadLocal<>();

	public static User getUser() {
		return CONTEXT.get();
	}

	public static void setUser(User user) {
		CONTEXT.set(user);
	}

	public static void clear() {
		CONTEXT.remove();
	}
}
