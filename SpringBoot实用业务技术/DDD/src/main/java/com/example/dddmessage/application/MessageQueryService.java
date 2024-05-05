package com.example.dddmessage.application;

import com.example.dddmessage.domain.aggregate.message.entity.Message;

import java.util.List;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface MessageQueryService {

    /**
     * 获取未读消息的数量
     * @param userId
     * @return
     */
    int getUnreadMessageTotal(int userId);

    /**
     * 获取往来消息列表
     * @param userId
     * @param contactId
     * @param size
     * @return
     */
    List<Message> getContactMessageList(int userId,int contactId,int size);
}