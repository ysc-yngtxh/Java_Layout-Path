package com.example.controller;

import com.example.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    // ✨ 注入AI Service（框架自动生成代理对象，直接使用）
    @Autowired
    private AssistantService assistantService;

    // 测试1：Java技术问答 → http://localhost:8080/ai/ask?question=Spring Bean生命周期
    @GetMapping("/ai/ask")
    public String askJava(@RequestParam String question) {
        return assistantService.chatWithJavaExpert(question);
    }

    // 测试2：多参数翻译 → http://localhost:8080/ai/translate?content=Hello&sourceLang=英文&targetLang=中文
    @GetMapping("/ai/translate")
    public String translate(@RequestParam String content,
                            @RequestParam String sourceLang,
                            @RequestParam String targetLang) {
        return assistantService.translate(content, sourceLang, targetLang);
    }

}
