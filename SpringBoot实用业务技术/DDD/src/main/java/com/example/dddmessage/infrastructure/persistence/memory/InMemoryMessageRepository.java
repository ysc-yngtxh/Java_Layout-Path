package com.example.dddmessage.infrastructure.persistence.memory;

import com.example.dddmessage.domain.aggregate.message.entity.Message;
import com.example.dddmessage.domain.aggregate.message.repository.MessageRepository;
import com.example.dddmessage.domain.shared.enums.MessageStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Repository
public class InMemoryMessageRepository implements MessageRepository {
    private final Map<Long, Message> messageRepositories = new ConcurrentHashMap<>();

    @Override
    public Message find(long messageId) {
        return messageRepositories.get(messageId);
    }

    @Override
    public void save(Message message) {
        messageRepositories.put(message.getMessageId(), message);
    }

    /**
     * 判断接受者是当前用户，且消息未读，最后统计总数
     * @param userId
     * @return
     */
    @Override
    public int getUnreadMessageTotal(int userId) {
        return (int) messageRepositories.values()
                .stream()
                .filter(v -> v.getStatus().sameValueAs(MessageStatus.NORMAL))
                .filter(v -> v.getReceiver().getUserId() == userId)
                .filter(v -> !v.isRead())
                .count();
    }

    /**
     * 往来消息
     *      发送者等于当前用户时，检查发送者是否删除
     *      接受者等于当前用户时，检查接受者是否删除
     * @param userId
     * @param contactId
     * @param size
     * @return
     */
    @Override
    public List<Message> getContactMessageList(int userId, int contactId, int size) {
        return messageRepositories.values()
                .stream()
                .filter(v -> v.getStatus().sameValueAs(MessageStatus.NORMAL))
                .filter(v ->
                    (v.getSender().getUserId() == userId && v.getReceiver().getUserId() == contactId && !v.isSenderDeleted())
                    ||
                    (v.getReceiver().getUserId() == userId &&  v.getSender().getUserId() == contactId && !v.isReceiverDeleted())
                )
                .collect(Collectors.toList());
    }
}
