package com.example.plugin;

/**
 * 拦截器接口，所有自定义拦截器必须实现此接口
 */
public interface Interceptor {

    /**
     * 插件的核心逻辑实现
     */
    Object intercept(Invocation invocation) throws Throwable;

    /**
     * 对被拦截对象进行代理
     */
    Object plugin(Object target);
}
