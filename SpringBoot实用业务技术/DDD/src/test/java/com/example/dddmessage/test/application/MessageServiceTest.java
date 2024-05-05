package com.example.dddmessage.test.application;

import com.example.dddmessage.application.MessageCommandService;
import com.example.dddmessage.application.MessageQueryService;
import com.example.dddmessage.domain.shared.enums.MessageCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageCommandService messageCommandService;

    @Autowired
    private MessageQueryService messageQueryService;

    @Test
    public void test(){
        messageCommandService.createMessage(1, MessageCategory.CHAT.getCode(), 1, 3, "xxx ");
        messageCommandService.createMessage(2, MessageCategory.CHAT.getCode(), 1, 4, "xxx ");
        messageCommandService.createMessage(3, MessageCategory.CHAT.getCode(), 1, 5, "xxx ");
        messageCommandService.createMessage(4, MessageCategory.CHAT.getCode(), 2, 3, "xxx ");
        messageCommandService.createMessage(5, MessageCategory.CHAT.getCode(), 2, 4, "xxx ");
        messageCommandService.createMessage(6, MessageCategory.CHAT.getCode(), 2, 5, "xxx ");
        messageCommandService.createMessage(7, MessageCategory.CHAT.getCode(), 1, 3, "xxx ");
        messageCommandService.createMessage(8, MessageCategory.CHAT.getCode(), 1, 8, "xxx ");
        messageCommandService.createMessage(9, MessageCategory.CHAT.getCode(), 1, 9, "xxx ");
        // receiver % 3 == 0 已读
        Assertions.assertEquals(0, messageQueryService.getUnreadMessageTotal(3));
        Assertions.assertEquals(2, messageQueryService.getUnreadMessageTotal(4));
        Assertions.assertEquals(2, messageQueryService.getContactMessageList(1, 3, 10).size());
        // 测回一条消息
        messageCommandService.recallMessage(1, 1);
        Assertions.assertEquals(1,messageQueryService.getContactMessageList(1, 3, 10).size());
        // 测回一条消息
        messageCommandService.recallMessage(1, 2);
        Assertions.assertEquals(1, messageQueryService.getUnreadMessageTotal(4));
    }
}
