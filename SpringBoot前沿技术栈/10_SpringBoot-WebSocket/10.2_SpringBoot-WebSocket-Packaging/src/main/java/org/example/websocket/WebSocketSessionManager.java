package org.example.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketSessionManager {

    // 存储 WebSocket 连接，key 为 userId
    private static final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    // 添加用户连接
    public static void addSession(String userId, WebSocketSession session) {
        sessions.put(userId, session);
    }

    // 移除用户连接
    public static void removeSession(String userId) {
        sessions.remove(userId);
    }

    // 发送消息
    public static void sendMessage(String userId, String message) {
        WebSocketSession session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
