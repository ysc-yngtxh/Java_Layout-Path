package com.example.dddmessage.domain.aggregate.message.event;

import com.example.dddmessage.domain.aggregate.message.entity.Message;
import com.example.dddmessage.domain.shared.event.AbstractEvent;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public class MessageCreatedEvent extends AbstractEvent<Message> {
    public MessageCreatedEvent(Message message) {
        super(message);
    }
}
