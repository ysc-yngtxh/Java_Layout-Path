package org.example.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	// 这个类用于配置WebSocket消息代理和注册STOMP（Simple Text Oriented Messaging Protocol）端点

	// 配置消息代理，启用简单的内存消息代理，并设置应用程序前缀
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// 启用简单的内存消息代理，处理以"/topic"开头的消息
		config.enableSimpleBroker("/topic");
		// 设置应用程序前缀，处理以"/app"开头的消息
		config.setApplicationDestinationPrefixes("/app");
		// 设置用户目的地前缀，处理以"/user/"开头的消息
		config.setUserDestinationPrefix("/user/");
	}

	// 注册STOMP端点，指定WebSocket连接的端点，并启用SockJS支持
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websocket-endpoint") // 定义WebSocket连接的端点
		        .withSockJS();                             // 启用SockJS支持，允许浏览器不支持WebSocket时使用SockJS进行降级连接
	}

}
