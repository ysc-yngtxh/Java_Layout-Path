package com.example.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

	// ServerEndpointExporter类注册 Bean 作用:
	//     1、它会扫描所有使用 @ServerEndpoint 注解的类，并将其注册为 WebSocket 端点，
	//        使其能够处理客户端的 WebSocket 连接请求。
	//     2、使用 JSR-356 标准的 @ServerEndpoint 注解时，默认是不感知 Spring 的依赖注入（DI）
	//        而 ServerEndpointExporter 注入Bean 会确保 @ServerEndpoint 类中的 @Autowired 等 Spring 注解生效，
	//        使其能使用 Spring 管理的 Bean（如 Service、Component）。
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

}
