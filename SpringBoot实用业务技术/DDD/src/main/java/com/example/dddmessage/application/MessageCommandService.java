package com.example.dddmessage.application;

import com.example.dddmessage.shared.Result;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface MessageCommandService {

	/**
	 * 创建消息
	 *
	 * @param messageId
	 * @param catId
	 * @param sender
	 * @param receiver
	 * @param content
	 */
	void createMessage(long messageId, int catId, int sender, int receiver, String content);

	/**
	 * 撤回消息
	 *
	 * @param userId
	 * @param messageId
	 */
	Result<Void> recallMessage(int userId, long messageId);
}
