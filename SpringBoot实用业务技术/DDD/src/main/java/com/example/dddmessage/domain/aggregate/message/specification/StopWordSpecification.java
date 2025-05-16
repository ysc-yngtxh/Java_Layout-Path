package com.example.dddmessage.domain.aggregate.message.specification;

import com.example.dddmessage.domain.aggregate.message.entity.Message;
import com.example.dddmessage.domain.shared.enums.MessageCategory;
import com.example.dddmessage.domain.shared.enums.MessageStatus;
import com.example.dddmessage.domain.shared.specification.AbstractSpecification;
import com.example.dddmessage.domain.shared.specification.InteractiveSpecification;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * 违禁词识别逻辑
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Component
public class StopWordSpecification extends AbstractSpecification<Message> implements InteractiveSpecification<Message, MessageStatus> {

	@Override
	public void notSatisfiedHandleBy(Message message, Consumer<MessageStatus> handle) {
		if (isSatisfiedBy(message)) {
			return;
		}
		handle.accept(MessageStatus.HAS_STOP_WORD);
	}

	@Override
	public boolean isSatisfiedBy(Message message) {
		// 只验证聊天消息,系统消息不校验违禁
		if (!MessageCategory.CHAT.sameValueAs(message.getCategory())) {
			return true;
		}
		String content = message.getContent().getContent();
		// TODO 通过系统违禁词列表，匹配消息内容是否包含违禁词
		if (content.contains("线下交易")) {
			return false;
		}
		return true;
	}
}
