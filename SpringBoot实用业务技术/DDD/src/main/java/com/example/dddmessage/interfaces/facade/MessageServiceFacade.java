package com.example.dddmessage.interfaces.facade;

import com.example.dddmessage.shared.Result;
import com.example.dddmessage.interfaces.facade.command.SendMessageCommand;
import com.example.dddmessage.interfaces.facade.dto.MessageDTO;

import java.util.List;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface MessageServiceFacade {

	/**
	 * 发送消息
	 *
	 * @param sendMessageCommand
	 * @return
	 */
	void sendMessage(SendMessageCommand sendMessageCommand);

	/**
	 * 撤回消息
	 *
	 * @param userId    当前会话用户Id
	 * @param messageId 消息ID
	 */
	Result<Void> recallMessage(int userId, long messageId);

	/**
	 * 获取未读消息的数量
	 *
	 * @param userId
	 * @return
	 */
	int getUnreadMessageTotal(int userId);

	/**
	 * 获取往来消息列表
	 *
	 * @param userId
	 * @param contactId
	 * @param size
	 * @return
	 */
	List<MessageDTO> getContactMessageList(int userId, int contactId, int size);
}
