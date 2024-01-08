package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.handler.BlockClassHandler;
import com.example.handler.FallbackClassHandler;
import com.example.service.SentinelClassService;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-08 12:48
 * @apiNote TODO
 */
@Service
public class SentinelClassServiceImpl implements SentinelClassService {

    public static final String RESOURCE_CLASS = "selectClassById";

    @Override
    // 注意：注解方式埋点不支持 private 方法。
    @SentinelResource(blockHandler = "selectClassByIdBlockHandler",
            blockHandlerClass = BlockClassHandler.class, fallbackClass = FallbackClassHandler.class,
            fallback = "selectClassByIdFallbackHandler")
    public String selectClassById(Integer id) {
        if (id == 2) {
            throw new RuntimeException("出异常了啊！");
        }
        return "{id: " + id + ", age: 25}";
    }
}
