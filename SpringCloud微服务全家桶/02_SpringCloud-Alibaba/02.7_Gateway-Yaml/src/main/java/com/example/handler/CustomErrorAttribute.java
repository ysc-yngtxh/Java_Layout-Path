package com.example.handler;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-05 23:00
 * @apiNote TODO
 */
@Component
public class CustomErrorAttribute extends DefaultErrorAttributes {

    // DefaultErrorAttributes是一个实现了ErrorAttributes接口的类，用于为异常生成错误属性。
    // 它为异常提供了默认的属性值，包括异常类名、异常消息等。在异常处理过程中，Spring会调用DefaultErrorAttributes的实例来创建错误的属性，
    // 然后将这些属性添加到错误的响应中。开发人员也可以通过扩展DefaultErrorAttributes类来自定义错误属性的生成方式。
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        // return super.getErrorAttributes(request, options);
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.NOT_FOUND);
        map.put("msg", "对不起，没有找到您要的资源");
        return map;
    }
}
