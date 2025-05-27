package com.example.controller;

import com.example.vo.ChatRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.api.DeepSeekApi;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@CrossOrigin(origins = "*")  // 允许跨域访问
public class ChatController {

	private final ChatClient chatClient;

	public ChatController(ChatClient.Builder builder) {
		this.chatClient = builder.defaultSystem("你是一个智能机器人,你的名字叫Spring AI智能机器人").build();
	}

	/**
	 * curl http://localhost:8080/chat/今天西安天气咋样
	 *
	 * @param message
	 * @return String
	 */
	@GetMapping(value = "/chat/{message}")
	public String chat(@PathVariable("message") String message) {
		// prompt()  构建提示
		// user()    设置用户消息
		// call()    发送请求并获取响应
		// content() 获取响应内容
		String content = chatClient.prompt()
		                           .user(message)
		                           .call()
		                           .content();
		System.out.println("Chat response: " + content);

		// 也可以使用 Prompt 对象来构建提示
		String content1 = chatClient.prompt(message)
		                            .call()
		                            .content();
		System.out.println("Chat response1: " + content1);

		return content;
	}

	@PostMapping(value = "/chat/stream/{message}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> streamChat(@PathVariable("message") String message) {
		return chatClient.prompt(message)
				.stream().content();
		                 // .stream().content().map(content -> ServerSentEvent.builder(content).event("message").build());
		                 // 问题回答结速标识，以便前端消息展示处理
		                 // .concatWithValues(ServerSentEvent.builder("[DONE]").build())
		                 // .onErrorResume(e -> Flux.just(ServerSentEvent.builder("Error: " + e.getMessage()).event("error").build()));
	}

}
