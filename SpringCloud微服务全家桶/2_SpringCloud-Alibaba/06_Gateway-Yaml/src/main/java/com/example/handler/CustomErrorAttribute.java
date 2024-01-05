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
 * @dateTime 2024-01-05 23:36
 * @apiNote TODO
 */
@Component
public class CustomErrorAttribute extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        // return super.getErrorAttributes(request, options);
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.NOT_FOUND);
        map.put("msg", "对不起，没有找到您要的资源");
        return map;
    }
}
