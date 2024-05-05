package com.example.dddmessage.test.domain.service;

import com.example.dddmessage.domain.aggregate.message.entity.Message;
import com.example.dddmessage.domain.service.MessageDomainService;
import com.example.dddmessage.domain.shared.enums.MessageCategory;
import com.example.dddmessage.domain.shared.enums.MessageStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageDomainServiceTest {

    @Autowired
    private MessageDomainService messageDomainService;


    @Test
    public void createMessage(){
        Message message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 100, 1, "xx");
        Assert.assertEquals(MessageStatus.IN_BLOCKED_LIST, message.getStatus());

        message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 1, 2, "xxx");
        Assert.assertEquals(MessageStatus.NORMAL, message.getStatus());

        message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 1, 2, "线下交易");
        Assert.assertEquals(MessageStatus.HAS_STOP_WORD, message.getStatus());

        message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 1, 2, "支付宝");
        Assert.assertEquals(MessageStatus.UN_SAFE, message.getStatus());

        message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 1, 3, "xxx");
        Assert.assertTrue(message.isRead());

        message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 1, 2, "xxx");
        Assert.assertFalse(message.isRead());
    }
}
