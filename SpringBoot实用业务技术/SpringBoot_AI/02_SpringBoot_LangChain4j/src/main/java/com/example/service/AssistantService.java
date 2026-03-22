package com.example.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

// ✨ 关键注解：标记为AI服务，框架自动生成实现类
@AiService
public interface AssistantService {

    // 📌 注解说明：
    // @SystemMessage：系统提示词（定义AI角色、行为约束）
    // @UserMessage：用户输入占位符（接收前端传入的参数）
    @SystemMessage("你是一个专业的Java开发助手，回答简洁、准确，只提供技术干货")
    String chatWithJavaExpert(@UserMessage String userQuestion);

    // 支持多参数、自定义提示词模板
    @SystemMessage("你是一个翻译专家，将{sourceLang}翻译成{targetLang}，保持语义不变")
    String translate(@UserMessage String content, String sourceLang, String targetLang);

    // ✅ @MemoryId：指定会话ID，框架自动管理该ID的对话上下文
    // 调用时传入唯一的sessionId（如用户ID），即可实现多轮对话上下文关联。
    String chat(@MemoryId String sessionId, @UserMessage String userMessage);

}
