package com.example.dddmessage.application.impl;

import com.example.dddmessage.application.MessageCommandService;
import com.example.dddmessage.application.MessageQueryService;
import com.example.dddmessage.shared.Error;
import com.example.dddmessage.shared.Result;
import com.example.dddmessage.domain.aggregate.message.entity.Message;
import com.example.dddmessage.domain.aggregate.message.event.MessageCreatedEvent;
import com.example.dddmessage.domain.aggregate.message.event.MessageRecalledEvent;
import com.example.dddmessage.domain.aggregate.message.repository.MessageRepository;
import com.example.dddmessage.domain.service.MessageDomainService;
import com.example.dddmessage.domain.shared.enums.MessageCategory;
import com.example.dddmessage.domain.shared.event.DomainEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Service
public class MessageServiceImpl implements MessageCommandService, MessageQueryService {
    private final MessageRepository messageRepository;
    private final MessageDomainService messageDomainService;
    private final DomainEventPublisher domainEventPublisher;

    public MessageServiceImpl(MessageRepository messageRepository, MessageDomainService messageDomainService, DomainEventPublisher domainEventPublisher) {
        this.messageRepository = messageRepository;
        this.messageDomainService = messageDomainService;
        this.domainEventPublisher = domainEventPublisher;
    }

    /**
     * 创建消息
     * @param messageId
     * @param catId
     * @param sender
     * @param receiver
     * @param content
     */
    @Override
    public void createMessage(long messageId, int catId, int sender, int receiver, String content) {
        MessageCategory category = MessageCategory.getByCode(catId);
        Message message = this.messageDomainService.createMessage(messageId, category,sender, receiver, content);
        this.messageRepository.save(message);
        this.domainEventPublisher.emit(new MessageCreatedEvent(message));
    }

    /**
     * 撤回消息
     * @param userId
     * @param messageId
     */
    @Override
    public Result<Void> recallMessage(int userId, long messageId) {
        Message message = this.messageRepository.find(messageId);
        if (message == null){
            return Result.create(Error.ENTITY_NOT_FUND);
        }
        //不是发送者不能撤销消息
        boolean isSender = Optional.ofNullable(message.getSender()).filter(v -> v.getUserId() == userId).isPresent();
        if (!isSender){
            return Result.create(Error.NOT_OPERATION_PERMISSION);
        }
        //调用领域服务撤销消息
        Result<Void> result = this.messageDomainService.recall(message);
        if(result.isSuccess()){
            this.messageRepository.save(message);
            this.domainEventPublisher.emit(new MessageRecalledEvent(message));
        }
        return result;
    }
    /**
     * 获取未读消息的数量
     * @param userId
     * @return
     */
    @Override
    public int getUnreadMessageTotal(int userId) {
        return messageRepository.getUnreadMessageTotal(userId);
    }

    /**
     * 获取往来消息列表
     * @param userId
     * @param contactId
     * @param size
     * @return
     */
    @Override
    public List<Message> getContactMessageList(int userId, int contactId, int size) {
        return messageRepository.getContactMessageList(userId,contactId,size);
    }
}