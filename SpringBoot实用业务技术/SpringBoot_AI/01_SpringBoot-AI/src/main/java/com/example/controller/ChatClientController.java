package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.UUID;

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
				builder.defaultSystem("你是一个智能机器人，你的名字叫Spring AI智能机器人").build();
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
	 * 流式响应就是将生成的文本内容一段一段的显示出来。
	 *
	 * @param message String
	 * @return Flux<String>：适合“傻瓜式”的简单数据流推送，Spring 会自动帮你封装成 SSE 格式，但你无法控制消息的 ID、重连时间等高级属性。
	 * 特点：
	 *    异步非阻塞：使用 Reactor 的 Flux 实现流式响应
	 *    实时分块返回：AI 生成的内容会分成多个小块实时返回
	 *    返回类型：返回 Flux<String>，表示一个字符串流
     * 优点：代码极其简洁，适合快速验证或不需要复杂逻辑的场景。
     * 缺点：
     *    无状态：客户端无法感知消息的具体 ID。如果网络断开，浏览器虽然会自动重连，但服务端不知道客户端最后收到了哪条消息，无法精准补发。
     *    单一类型：客户端只能接收到默认的 message 事件，无法区分是“正常数据”还是“系统通知”。
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
	 * @return Flux<ServerSentEvent<String>>：适合“专业级”的实时通信，允许你显式定义 SSE 协议的每一个字段（ID、事件类型、重试时间），是实现断点续传和高可靠推送的必选项。
     *
     * 优点：
     *    1、断点续传 (关键)：通过设置 .id()，客户端重连时会携带 Last-Event-ID 请求头。服务端可以根据这个 ID 从断开的地方继续发送数据，这对于 AI 流式输出或即时通讯至关重要。
     *    2、自定义事件：通过 .event()，前端可以使用 source.addEventListener('customEvent', ...) 来监听特定类型的消息，而不是把所有数据都塞进 onmessage 里处理。
     *    3、控制重连：通过 .retry()，你可以告诉客户端“如果断了，请等 10 秒再连”，而不是让客户端频繁重试拖垮服务器。
	 */
	@PostMapping(value = "/chat/stream/sse/{message}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<String>> streamChatServerSentEvent(@PathVariable("message") String message) {
		Flux<ServerSentEvent<String>> serverSentEventFlux =
				chatClient.prompt(message)
				          .stream()
		                  .content()
		                  .map(item ->
                                  ServerSentEvent.builder(item)                     // 数据内容
                                          .id(UUID.randomUUID().toString())           // 消息ID：支持断点续传
                                          .event("message")                        // 事件类型：前端可针对性监听
                                          .retry(Duration.ofSeconds(3000)) // 重连时间：建议客户端3秒后重连
                                          .build()
                          )
		                  // 问题回答结速标识，以便前端消息展示处理
		                  .concatWithValues(ServerSentEvent.builder("[DONE]").build())
		                  .onErrorResume(e -> Flux.just(ServerSentEvent.builder("Error: " + e.getMessage()).event("error").build()));
		System.out.println("Stream Chat SSE response: " + serverSentEventFlux);
		return serverSentEventFlux;
	}

    // TODO 场景建议
    //  如果你在做 AI 聊天（打字机效果）：
    //    推荐使用 Flux<ServerSentEvent<String>>。虽然 Flux<String> 也能让字一个个蹦出来，但加上 id 字段可以防止网络波动导致回答中断或重复，提升用户体验。
    //  如果你在做简单的服务器监控大屏：
    //    可以使用 Flux<String>。如果偶尔丢包或重连，只要拿到最新的数据即可，不需要关心之前的状态。
    //    如果你需要前端根据消息类型做不同UI渲染（例如：一条是“系统通知”，一条是“新订单”）：必须使用 Flux<ServerSentEvent<String>>，利用 event 字段来区分消息类型。
}
