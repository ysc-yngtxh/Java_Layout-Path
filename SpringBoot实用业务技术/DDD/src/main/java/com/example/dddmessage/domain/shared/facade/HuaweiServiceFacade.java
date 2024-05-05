package com.example.dddmessage.domain.shared.facade;

import com.example.dddmessage.domain.aggregate.notice.entity.valueobject.AppMessage;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface HuaweiServiceFacade {
    /**
     * 发布消息到华为推送平台
     *
     * @param appMessage
     */
    void publish(AppMessage appMessage);
}
