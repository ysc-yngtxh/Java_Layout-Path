package com.example.websocket;

import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
// ServerEndpointConfig.Configurator作用
//     1、它允许我们在 WebSocket 握手过程中修改 ServerEndpointConfig 的配置，
//        例如添加自定义的握手处理逻辑、修改端点的路径等。
//     2、它可以用于在握手过程中获取请求头信息（如 cookie），并将其存储到 ServerEndpointConfig 的 UserProperties 中，
//        使得在 WebSocket 端点中可以通过 UserProperties 获取到这些信息。
// 注意：如果使用 Spring WebSocket 的 STOMP 协议，则不需要使用 ServerEndpointConfig.Configurator
public class WebSocketConfig extends ServerEndpointConfig.Configurator {

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

	// TODO 实现 ServerEndpointConfig.Configurator 的方法【功能类似于进行WebSocket连接之前的拦截器】
	// 在握手过程中，获取请求头中的 cookie 信息，并将其存储到 ServerEndpointConfig 的 UserProperties 中
	// 这样在 WebSocket 端点中可以通过 UserProperties 获取到 cookie 信息
	// 注意：此方法仅在使用 JSR-356 标准的 WebSocket 时有效，并且需要在 @ServerEndpoint 注解的 configurator 属性中指定此类才能生效。
	// 	    如果使用 Spring WebSocket 的 STOMP 协议，则需要在 STOMP 握手时处理 cookie。
	@Override
	public void modifyHandshake(ServerEndpointConfig sec,
	                            HandshakeRequest request, HandshakeResponse response) {
		// 获取请求头中的 cookie
		List<String> cookies = request.getHeaders().get("Cookie");
		if (cookies != null && !cookies.isEmpty()) {
			String cookieStr = cookies.getFirst();
			Map<String, String> cookieMap = new HashMap<>();

			// 解析 cookie 字符串
			String[] cookieArray = cookieStr.split(";");
			for (String cookie : cookieArray) {
				String[] parts = cookie.trim().split("=");
				if (parts.length == 2) {
					cookieMap.put(parts[0].trim(), parts[1].trim());
				}
			}
			// 将 cookie 信息存储到 ServerEndpointConfig 的 UserProperties 中
			sec.getUserProperties().put("cookies", cookieMap);
		}
		super.modifyHandshake(sec, request, response);
	}

}
