package com.example.dddmessage.domain.shared.facade;

import com.example.dddmessage.domain.aggregate.message.entity.valueobject.User;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface UserServiceFacade {
    /**
     * 获取用户
     *
     * @param userId
     * @return
     */
    User getUser(int userId);
}
