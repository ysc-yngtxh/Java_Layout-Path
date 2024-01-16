package com.example.openService.fallback;

import com.example.openService.EchoService;
import org.springframework.stereotype.Component;

@Component
public class EchoFallbackHandler implements EchoService {
    public String echo(Integer id) {
        return "echo fallback";
    }
}