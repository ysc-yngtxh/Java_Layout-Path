package com.example.dddmessage.domain.service;

import com.example.dddmessage.shared.Result;
import com.example.dddmessage.domain.aggregate.message.entity.Message;
import com.example.dddmessage.domain.shared.enums.MessageCategory;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface MessageDomainService {

	/**
	 * 创建消息
	 *
	 * @param messageId
	 * @param senderId
	 * @param receiverId
	 * @param content
	 * @return
	 */
	Message createMessage(long messageId, MessageCategory category, int senderId, int receiverId, String content);

	/**
	 * 撤销消息
	 *
	 * @param message
	 */
	Result<Void> recall(Message message);
}
