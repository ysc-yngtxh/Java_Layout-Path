package com.example.tenant;

import com.example.entity.User;

public class TenantContextHolder {

    private static final ThreadLocal<User> CONTEXT = new ThreadLocal<>();

    public static void setTbUser(User user) {
        CONTEXT.set(user);
    }

    public static User getTbUser() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
