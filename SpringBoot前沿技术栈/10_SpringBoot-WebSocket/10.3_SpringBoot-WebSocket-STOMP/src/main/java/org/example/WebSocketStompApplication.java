package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSocketStompApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketStompApplication.class, args);
	}

	/* 实现 WebSocket 的方式三
	 *     使用Spring STOMP 协议封装的 WebSocket 应用
	 *     STOMP 是一个简单的文本协议，基于 WebSocket 之上
	 *     STOMP 协议可以在 WebSocket 之上实现消息的发布/订阅模式
	 */

}
