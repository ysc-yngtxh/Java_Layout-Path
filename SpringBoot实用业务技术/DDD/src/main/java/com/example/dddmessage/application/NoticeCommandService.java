package com.example.dddmessage.application;

import com.example.dddmessage.domain.aggregate.message.event.MessageCreatedEvent;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface NoticeCommandService {

	/**
	 * 创建消息通知
	 *
	 * @param messageCreatedEvent
	 */
	void createNotice(MessageCreatedEvent messageCreatedEvent);
}
