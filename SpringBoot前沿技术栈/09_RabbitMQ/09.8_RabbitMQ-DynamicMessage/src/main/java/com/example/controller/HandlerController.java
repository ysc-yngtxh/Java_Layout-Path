package com.example.controller;

import com.example.handler.HandlerRegistry;
import com.example.handler.MessageHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/handlers")
public class HandlerController {

    // 使用构造器注入 HandlerRegistry 对象
    private final HandlerRegistry handlerRegistry;
    public HandlerController(HandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    @PostMapping("/{messageType}")
    public String registerHandler(
            @PathVariable String messageType,
            @RequestBody MessageHandler handler) {
        
        handlerRegistry.addHandler(messageType, handler);
        return "Handler registered for type: " + messageType;
    }

    @DeleteMapping("/{messageType}")
    public String unregisterHandler(@PathVariable String messageType) {
        handlerRegistry.removeHandler(messageType);
        return "Handler removed for type: " + messageType;
    }
}