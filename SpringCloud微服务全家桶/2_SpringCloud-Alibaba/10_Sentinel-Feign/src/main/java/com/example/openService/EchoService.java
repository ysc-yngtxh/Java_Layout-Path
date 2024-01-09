package com.example.openService;

import com.example.handler.EchoFallbackHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nacos-provider", fallback = EchoFallbackHandler.class)
public interface EchoService {
    @GetMapping(value = "/provider/{id}")
    String echo(@PathVariable("id") Integer id);
}
