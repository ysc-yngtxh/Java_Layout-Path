package com.example.mybatis.factory;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-05 00:00
 * @apiNote TODO Mapper工厂
 */
public class MapperProxyFactory {

    public static <T> T getMapper(Class<T> mapperInterface){
        // JDK的动态代理
        Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader()
                , new Class[]{mapperInterface}
                , (Object proxy, Method method, Object[] args) -> {
                    // 解析sql ---> 执行sql ---> 结果(包装)
                    return null;
                });
    }
}
