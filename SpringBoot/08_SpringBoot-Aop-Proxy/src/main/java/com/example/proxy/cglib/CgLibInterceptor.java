package com.example.proxy.cglib;

import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 22:16
 * @apiNote TODO
 */
@Data
@AllArgsConstructor
public class CgLibInterceptor implements MethodInterceptor {

    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib在调用前增强一下处理逻辑");
        Object invoke = method.invoke(target, objects);
        System.out.println("Cglib在调用后增强一下处理逻辑");
        return invoke;
    }

}
