package org.example.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket // 启用WebSocket支持
public class WebSocketServer implements WebSocketConfigurer {

	// 这个类用于配置WebSocket处理器和注册WebSocket端点
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new MyWebSocketHandler(), "/ws") // 注册WebSocket处理器，指定端点为/ws
		        .setAllowedOrigins("*"); // 允许所有来源，生产环境需指定域名
	}

}
