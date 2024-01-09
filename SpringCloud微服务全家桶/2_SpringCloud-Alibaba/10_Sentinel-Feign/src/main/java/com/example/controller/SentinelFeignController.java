package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.openService.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelFeignController {

    @Autowired
    private EchoService echoService;

    public static final String SENTINEL_FEIGN_BY_ID = "sentinelFeignById";

    @GetMapping("/sentinelFeign/{id}")
    @SentinelResource(value = SENTINEL_FEIGN_BY_ID)
    public String sentinelFeignById(@PathVariable Integer id){
        return echoService.echo(id);
    }
}