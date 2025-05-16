package com.example.dddmessage.interfaces.facade.assembler;

import com.example.dddmessage.domain.aggregate.message.entity.Message;
import com.example.dddmessage.interfaces.facade.dto.MessageDTO;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public class MessageDTOAssembler {

	/**
	 * 消息实体转换为 {@link MessageDTO}
	 *
	 * @param message 消息实体
	 * @return 消息实体转换后的 MessageDTO
	 */
	public static MessageDTO toDTO(final Message message) {
		MessageDTO dto = new MessageDTO();
		dto.setMessageId(message.getMessageId());
		dto.setCatId(message.getCategory().getCode());
		dto.setSender(message.getSender().getUserId());
		dto.setSenderNickname(message.getSender().getNickname());
		dto.setSenderPhoto(message.getSender().getPhoto());
		dto.setReceiver(message.getReceiver().getUserId());
		dto.setReceiverNickname(message.getReceiver().getNickname());
		dto.setReceiverPhoto(message.getReceiver().getPhoto());
		dto.setContentTpl(message.getContent().getTpl());
		dto.setContent(message.getContent().getContent());
		dto.setRead(message.isRead());
		dto.setSendTime(message.getSendTime());
		return dto;
	}

}
