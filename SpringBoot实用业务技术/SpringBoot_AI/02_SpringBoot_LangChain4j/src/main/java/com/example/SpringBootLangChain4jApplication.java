package com.example;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootLangChain4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLangChain4jApplication.class, args);
    }

    /**
     * 配置 ChatMemoryProvider 以支持 @MemoryId
     * 这里使用 MessageWindowChatMemory，保留最近 10 条消息作为上下文
     */
    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        return sessionId -> MessageWindowChatMemory.withMaxMessages(10);
    }

}
