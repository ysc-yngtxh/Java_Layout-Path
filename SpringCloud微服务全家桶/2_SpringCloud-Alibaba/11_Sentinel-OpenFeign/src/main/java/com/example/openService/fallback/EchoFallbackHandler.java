package com.example.openService.fallback;

import com.example.openService.EchoService;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-16 20:07
 * @apiNote TODO
 */
@Component
public class EchoFallbackHandler implements EchoService {
    @Override
    public String echo(Integer id) {
        return "echo fallback";
    }
}
