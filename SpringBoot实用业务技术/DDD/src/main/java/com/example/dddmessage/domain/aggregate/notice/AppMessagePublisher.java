package com.example.dddmessage.domain.aggregate.notice;

import com.example.dddmessage.domain.aggregate.notice.entity.valueobject.AppMessage;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface AppMessagePublisher {
    /**
     * 发送 {@link AppMessage}
     *
     * @param appMessage
     */
    void publish(AppMessage appMessage);
}
