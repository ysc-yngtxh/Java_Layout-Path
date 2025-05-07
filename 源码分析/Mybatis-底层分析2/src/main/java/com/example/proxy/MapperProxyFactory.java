package com.example.proxy;

import com.example.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * 用于产生MapperProxy代理类
 */
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;
    private Class object;

    public MapperProxyFactory(Class<T> mapperInterface, Class object) {
        this.mapperInterface = mapperInterface;
        this.object = object;
    }

    public T newInstance(SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader()
                , new Class[]{mapperInterface}
                , new MapperProxy(sqlSession, object));
    }
}
