package org.example.websocket;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket // 启用WebSocket支持
public class WebSocketServer implements WebSocketConfigurer {

	// 注入自定义的 WebSocket拦截器
	@Bean
	public WebSocketInterceptor webSocketInterceptor() {
		return new WebSocketInterceptor();
	}
	// 注入自定义的 WebSocket处理器
	@Bean
	public WebSocketHandler webSocketHandler() {
		return new WebSocketHandler();
	}

	// 这个类用于配置 WebSocket处理器 和注册 WebSocket端点
	@Override
	public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/ws")        // 注册WebSocket处理器，指定端点为/ws
		        .addInterceptors(webSocketInterceptor()) // 添加拦截器
		        .setAllowedOrigins("*");                             // 允许所有来源，生产环境需指定域名
	}

}
