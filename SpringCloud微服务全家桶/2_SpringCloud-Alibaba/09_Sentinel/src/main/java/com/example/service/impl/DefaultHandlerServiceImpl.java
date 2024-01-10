package com.example.service.impl;

import com.example.service.DefaultHandlerService;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-10 16:07
 * @apiNote TODO
 */
@Service
public class DefaultHandlerServiceImpl implements DefaultHandlerService {

    public String defaultBlockRule(Integer id) {
        return "此时访问成功未被限流，且该资源限流为Sentinel默认限流处理逻辑！！！";
    }
}
