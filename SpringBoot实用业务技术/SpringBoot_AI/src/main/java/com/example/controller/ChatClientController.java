package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@MessageMapping("ai.service")
@CrossOrigin(origins = "*")  // 允许跨域访问
public class ChatClientController {

	// ChatClient Api 里是大模型通用的功能。
	// ChatMode Api 是大模型特有功能。假如用deepseek特有的模型功能需要用ChatMode Api
	private final ChatClient chatClient;

	public ChatClientController(ChatClient.Builder builder) {
		this.chatClient =
				builder.defaultSystem("你是一个智能机器人,你的名字叫Spring AI智能机器人").build();
	}

	/**
	 * curl http://localhost:8080/chat/今天西安天气咋样
	 * @param message String
	 * @return String
	 * 特点：
	 *    同步阻塞式：等待完整响应后才返回
	 *    简单请求-响应模式：适合短小快速的问答
	 *    返回类型：直接返回字符串内容
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

		// 也可以使用 Prompt 对象来构建提示，等同于上面的 prompt().user(message)
		String content1 = chatClient.prompt(message)
		                            .call()
		                            .content();
		System.out.println("Chat response2：" + content1);

		return content;
	}

	/**
	 * curl http://localhost:8080/chat/stream/今天西安天气咋样
	 * 流式响应就是将生成的文本内容一个字一个字的显示出来。
	 *
	 * @param message String
	 * @return Flux<String>
	 * 特点：
	 *    异步非阻塞：使用 Reactor 的 Flux 实现流式响应
	 *    实时分块返回：AI 生成的内容会分成多个小块实时返回
	 *    返回类型：返回 Flux<String>，表示一个字符串流
	 * 注意事项：前端需要使用能够处理流式响应的技术（如 Fetch API、Axios 等）
	 */
	@PostMapping(value = "/chat/stream/{message}")
	public Flux<String> streamChat(@PathVariable("message") String message) {
		Flux<String> content = chatClient.prompt(message)
		                                 .stream()
		                                 .content();
		// content.doOnNext(System.out::print).subscribe(); // 订阅以触发流的处理
		return content;
	}

	/**
	 * 使用 Server-Sent Events (SSE) 进行流式响应
	 * 前端可以通过 EventSource 接收这些事件
	 *
	 * @param message String
	 * @return Flux<ServerSentEvent<String>>
	 */
	@PostMapping(value = "/chat/stream/sse/{message}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<String>> streamChatServerSentEvent(@PathVariable("message") String message) {
		Flux<ServerSentEvent<String>> serverSentEventFlux =
				chatClient.prompt(message)
				          .stream()
		                  .content()
		                  .map(item -> ServerSentEvent.builder(item).event("message").build())
		                  // 问题回答结速标识，以便前端消息展示处理
		                  .concatWithValues(ServerSentEvent.builder("[DONE]").build())
		                  .onErrorResume(e -> Flux.just(ServerSentEvent.builder("Error: " + e.getMessage()).event("error").build()));
		System.out.println("Stream Chat SSE response: " + serverSentEventFlux);
		return serverSentEventFlux;
	}

}
