package com.example.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 22:26
 * @apiNote TODO
 */
@Data
@AllArgsConstructor
public class JDKProxy implements InvocationHandler {

    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
