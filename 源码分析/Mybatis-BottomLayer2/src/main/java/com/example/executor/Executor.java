package com.example.executor;

/**
 * 执行器
 */
public interface Executor {

    <T> T query(String statement, Object[] parameter, Class pojo);
}
