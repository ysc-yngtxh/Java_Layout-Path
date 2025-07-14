package org.example.service;

import org.example.websocket.WebSocketSessionManager;
import org.springframework.stereotype.Service;

/**
 * @Author 游家纨绔
 * @Description TODO 
 * @Date 2025-07-13 18:10:00
 */
@Service
public class SocketService {

	public void updateOrderStatus(String orderId, String userId, String status) {
		// 更新数据库订单状态【这里暂省略数据库操作...】
		System.out.println("订单 " + orderId + " 状态变更为：" + status);
		// WebSocket 推送通知
		WebSocketSessionManager.sendMessage(userId, "您的订单 " + orderId + " 状态更新为：" + status);
	}

}
