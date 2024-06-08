package com.example.protocol.dispatcher;

import com.example.common.Invocation;
import com.example.register.LocalRegister;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-07 下午7:46
 * @apiNote TODO
 */
public class HttpServletHandler {

    @SneakyThrows
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        // 从请求体中反序列化得到请求参数
        Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();

        // 获取实现类
        Class<?> classImpl = LocalRegister.get(invocation.getInterfaceName(), "1.0.0");
        // 获取实现类中指定的方法
        Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
        // 利用反射执行方法
        String result = (String) method.invoke(classImpl.newInstance(), invocation.getParameters());
        // 将执行结果写入响应体
        IOUtils.write(result, resp.getOutputStream());
    }
}
