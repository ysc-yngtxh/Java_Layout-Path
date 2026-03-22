package com.example;

import com.example.service.AssistantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootLangChain4jApplicationTests {

    @Autowired
    private AssistantService assistantService;

    @Test
    void testAiService() {
        String result = assistantService.chatWithJavaExpert("什么是LangChain4j？");
        System.out.println("AI响应：\n" + result);
    }

}
