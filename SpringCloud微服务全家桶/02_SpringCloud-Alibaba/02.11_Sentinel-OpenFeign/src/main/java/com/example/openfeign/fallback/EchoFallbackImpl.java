package com.example.openfeign.fallback;

import com.example.openfeign.EchoOpenFeign;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-16 20:00
 * @apiNote TODO
 */
@Component
public class EchoFallbackImpl implements EchoOpenFeign {

    @Override
    public String echo(Integer id) {
        return "echo fallback";
    }
}
