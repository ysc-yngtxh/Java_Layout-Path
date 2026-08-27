package com.example.openfeign;

import com.example.openfeign.fallback.EchoFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nacos-provider", fallback = EchoFallbackImpl.class)
public interface EchoOpenFeign {

    @GetMapping(value = "/provider/{id}")
    String echo(@PathVariable("id") Integer id);
}
