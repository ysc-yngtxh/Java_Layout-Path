package com.example.dddmessage.domain.aggregate.notice;

import com.example.dddmessage.domain.aggregate.notice.entity.valueobject.SocketMessage;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface SocketMessagePublisher {

	/**
	 * 发送 {@link SocketMessage}
	 *
	 * @param socketMessage
	 */
	void publish(SocketMessage socketMessage);
}
