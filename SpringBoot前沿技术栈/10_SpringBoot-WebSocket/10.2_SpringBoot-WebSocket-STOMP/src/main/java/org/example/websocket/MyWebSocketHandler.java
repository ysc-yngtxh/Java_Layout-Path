package org.example.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {

    // 客户端连接建立时调用，可发送欢迎消息。
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("新连接建立: " + session.getId());
        session.sendMessage(new TextMessage("欢迎连接 WebSocket 服务器！"));
    }

    // 接收客户端发送的文本消息，并返回相同的内容。
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("收到消息: " + message.getPayload());
        session.sendMessage(new TextMessage("服务器收到: " + message.getPayload()));
    }

    // 连接关闭时执行清理操作。
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("连接关闭: " + session.getId());
    }

}
