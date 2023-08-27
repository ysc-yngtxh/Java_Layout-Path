package com.example.tenant;

import com.example.entity.TbUser;

public class TenantContextHolder {

    private static final ThreadLocal<TbUser> CONTEXT = new ThreadLocal<>();

    public static void setTbUser(TbUser tbUser) {
        CONTEXT.set(tbUser);
    }

    public static TbUser getTbUser() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}