package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAIApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAIApplication.class, args);
	}

    // 在 Spring AI 框架中，ChatClient 和 ChatModel 是两个核心组件，它们的区别主要体现在抽象层级和使用场景上。
    // 简单来说：
    // ChatModel 是底层接口，它直接负责与 AI 模型（如通义千问、OpenAI GPT）进行通信，提供基础的调用能力，灵活性高但需要处理更多细节。
    // ChatClient 是高层封装，它在 ChatModel 之上构建，提供了更简洁、功能更丰富的 API，旨在提升开发效率和简化复杂场景的实现。

    // ChatModel：底层核心，灵活控制
    // ChatModel 是 Spring AI 的核心，它定义了与 AI 模型交互的基本契约。当你需要对请求的每一个细节（如 temperature, top 等参数）进行精确控制，或者实现自定义的模型适配器时，会直接使用 ChatModel。
    // 它的使用方式相对“原始”，你需要手动组装 Prompt（提示词）对象，并处理返回的 ChatResponse。

    // ChatClient：高层封装，高效开发
    // ChatClient 是为了简化开发而生的。它在 ChatModel 的基础上，提供了一套流畅的链式调用 API（Fluent API），隐藏了底层的复杂性。
    // 它不仅让代码更易读、更简洁，还集成了许多企业级应用所需的高级功能，例如：
    //     1、提示词管理：方便地设置系统提示词 (system()) 和用户消息 (user())。
    //     2、对话记忆 (Chat Memory)：自动管理多轮对话的上下文。
    //     3、RAG (检索增强生成)：轻松集成向量数据库，为模型提供外部知识。
    //     4、结构化输出：可以直接将 AI 的文本响应转换为指定的 Java 对象（POJO）。

}
