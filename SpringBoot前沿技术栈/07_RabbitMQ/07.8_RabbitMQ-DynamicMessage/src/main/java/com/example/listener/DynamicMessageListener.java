package com.example.listener;

import com.example.handler.MessageHandler;
import com.rabbitmq.client.Channel;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-17 19:00
 * @apiNote TODO 动态消息监听器
 */
public class DynamicMessageListener implements ChannelAwareMessageListener {

	@Getter
	private final Map<String, MessageHandler> handlers = new ConcurrentHashMap<>();

	// 使用构造器注入 MessageConverter 对象
	private final MessageConverter messageConverter;

	public DynamicMessageListener(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}

	// // 提供动态注册方法
	// public void registerHandler(String messageType, MessageHandler handler) {
	//     handlers.put(messageType, handler);
	// }
	//
	// // 提供动态注销方法
	// public void unregisterHandler(String messageType) {
	//     handlers.remove(messageType);
	// }

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		try {
			// 从消息头获取类型标识（示例）
			String messageType = message.getMessageProperties().getHeader("messageType");
			MessageHandler handler = handlers.get(messageType);

			if (!Objects.isNull(handler)) {
				// 将消息转换为指定类型的对象
				Object payload = messageConverter.fromMessage(message);
				handler.handle(payload, channel, message.getMessageProperties().getDeliveryTag());
			} else {
				// 处理未找到处理器的情况（如记录日志、NACK等）
				channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
			}
		} catch (Exception e) {
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
		}
	}

}
