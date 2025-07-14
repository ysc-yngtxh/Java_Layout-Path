package org.example.websocket;

import java.util.Map;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {

    // 客户端连接建立时调用，可发送欢迎消息。
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 从请求参数获取 userId，例如 ws://localhost:8080/ws?userId=123
        Map<String, Object> params = session.getAttributes();
        String userId = (String) session.getAttributes().get("userId");
        if (StringUtils.hasText(userId)) {
            userId = "guest-" + session.getId(); // 如果没有 userId，则使用会话ID作为默认值
        }
        WebSocketSessionManager.addSession(userId, session);
        System.out.println("新连接建立: " + session.getId());
        session.sendMessage(new TextMessage("欢迎连接 WebSocket 服务器！"));
    }

    // 接收客户端发送的文本消息，并返回相同的内容。
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("收到消息: " + message.getPayload());
        session.sendMessage(new TextMessage("服务器收到: " + message.getPayload()));
    }

    // 连接关闭时执行清理操作。
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String userId = (String) session.getAttributes().get("userId");
        if (StringUtils.hasText(userId)) {
            WebSocketSessionManager.removeSession(userId);
        }
        System.out.println("连接关闭: " + session.getId());
    }

    // 处理传输错误，可以记录日志或执行其他操作。
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        System.err.println("传输错误: " + exception.getMessage());
    }

}
