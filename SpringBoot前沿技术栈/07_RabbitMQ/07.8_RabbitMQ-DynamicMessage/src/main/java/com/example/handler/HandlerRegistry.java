package com.example.handler;

import com.example.listener.DynamicMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-17 19:00
 * @apiNote TODO 消息处理器注册中心
 */
@Component
public class HandlerRegistry {

	// 使用构造器注入 DynamicMessageListener 对象
	private final DynamicMessageListener dynamicMessageListener;

	public HandlerRegistry(DynamicMessageListener dynamicMessageListener) {
		this.dynamicMessageListener = dynamicMessageListener;
	}

	// 提供动态注册方法
	public void addHandler(String messageType, MessageHandler handler) {
		dynamicMessageListener.getHandlers().put(messageType, handler);
	}

	// 提供动态注销方法
	public void removeHandler(String messageType) {
		dynamicMessageListener.getHandlers().remove(messageType);
	}
}
