package com.example.framework;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-08 19:31
 * @apiNote TODO Bean定义
 */
public class BeanDefinition {

    // Bean的类对象
    private Class<?> clazz;
    // Bean的作用域
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
