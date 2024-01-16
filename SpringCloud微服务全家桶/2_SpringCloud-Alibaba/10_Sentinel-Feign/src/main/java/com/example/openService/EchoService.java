package com.example.openService;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.openService.fallback.EchoFallbackHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nacos-provider", fallback = EchoFallbackHandler.class)
public interface EchoService {
    public static final String SENTINEL_FEIGN_BY_ID = "sentinelFeignById";
    @GetMapping(value = "/provider/{id}")
    @SentinelResource(value = SENTINEL_FEIGN_BY_ID)
    String echo(@PathVariable("id") Integer id);
}
