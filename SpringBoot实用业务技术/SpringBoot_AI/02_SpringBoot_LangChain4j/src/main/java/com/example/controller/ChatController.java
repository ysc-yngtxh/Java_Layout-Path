package com.example.controller;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    // ✨ 核心：自动注入大模型客户端（框架已根据配置创建）
    @Autowired
    private ChatModel chatLanguageModel;

    // 测试接口：http://localhost:8080/chat?message=你好
    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        // ✅ 一行代码调用大模型，返回响应结果
        return chatLanguageModel.chat(message);
    }

}
