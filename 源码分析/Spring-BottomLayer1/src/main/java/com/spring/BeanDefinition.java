package com.spring;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-08 19:31
 * @apiNote TODO Bean定义
 */
public class BeanDefinition {

    private Class<?> clazz;
    private String scope;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
