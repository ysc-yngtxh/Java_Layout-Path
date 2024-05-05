package com.example.dddmessage.domain.shared.facade;

import com.example.dddmessage.domain.aggregate.notice.entity.valueobject.SocketMessage;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface ImSocketServiceFacade {

    /**
     * 发布消息到Im推送渠道
     *
     * @param socketMessage
     */
    void publish(SocketMessage socketMessage);
}
