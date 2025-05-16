package com.example.dddmessage.domain.aggregate.message.specification;

import com.example.dddmessage.domain.aggregate.message.entity.Message;
import com.example.dddmessage.domain.shared.enums.MessageStatus;
import com.example.dddmessage.domain.shared.specification.AndInteractiveSpecification;
import org.springframework.stereotype.Component;

/**
 * 消息状态业务逻辑
 *
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Component
public class MessageStatusSpecification extends AndInteractiveSpecification<Message, MessageStatus> {
	
	public MessageStatusSpecification(StopWordSpecification stopWordSpecification, BlockedSpecification blockedSpecification, SafetySpecification safetySpecification) {
		super(stopWordSpecification, blockedSpecification, safetySpecification);
	}

}
