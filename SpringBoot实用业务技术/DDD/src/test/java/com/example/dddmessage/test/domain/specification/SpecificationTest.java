package com.example.dddmessage.test.domain.specification;

import com.example.dddmessage.domain.aggregate.message.entity.Message;
import com.example.dddmessage.domain.aggregate.message.specification.BlockedSpecification;
import com.example.dddmessage.domain.aggregate.message.specification.SafetySpecification;
import com.example.dddmessage.domain.aggregate.message.specification.StopWordSpecification;
import com.example.dddmessage.domain.service.MessageDomainService;
import com.example.dddmessage.domain.shared.enums.MessageCategory;
import com.example.dddmessage.domain.shared.enums.MessageStatus;
import com.example.dddmessage.domain.shared.specification.AndInteractiveSpecification;
import com.example.dddmessage.domain.shared.specification.AndSpecification;
import com.example.dddmessage.domain.shared.specification.InteractiveSpecification;
import com.example.dddmessage.domain.shared.specification.Specification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@SpringBootTest
public class SpecificationTest {
    @Autowired
    private BlockedSpecification blockedSpecification;
    @Autowired
    private SafetySpecification safetySpecification;
    @Autowired
    private StopWordSpecification stopWordSpecification;
    @Autowired
    private MessageDomainService messageDomainService;

    /**
     * 测试组合的 specification api
     */
    @Test
    public void testCombinedSpecification(){
        Specification<Message> specification = new AndSpecification<Message>(blockedSpecification,safetySpecification)
                .and(stopWordSpecification);

        Message message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 1, 2, "你好");
        Assertions.assertTrue(specification.isSatisfiedBy(message));

        message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 1, 2, "你好,线下交易");
        Assertions.assertFalse(specification.isSatisfiedBy(message));

        message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 1, 2, "你好,支付宝");
        Assertions.assertFalse(specification.isSatisfiedBy(message));

        message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 100, 1, "你好");
        Assertions.assertFalse(specification.isSatisfiedBy(message));
    }

    /**
     * 测试组合的 可交互的specification api
     */
    @Test
    public void testInteractiveSpecification(){
        InteractiveSpecification<Message, MessageStatus> specification = new AndInteractiveSpecification<>(blockedSpecification,safetySpecification,stopWordSpecification);
        Message message = messageDomainService.createMessage(1L,MessageCategory.CHAT, 1, 2, "你好");
        Assertions.assertTrue(specification.isSatisfiedBy(message));

        message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 1, 2, "你好,线下交易");
        Assertions.assertFalse(specification.isSatisfiedBy(message));
        specification.notSatisfiedHandleBy(message,status -> Assertions.assertEquals(MessageStatus.HAS_STOP_WORD, status));

        message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 1, 2, "支付宝点击付款码，截个图");
        Assertions.assertFalse(specification.isSatisfiedBy(message));
        specification.notSatisfiedHandleBy(message,status -> Assertions.assertEquals(MessageStatus.UN_SAFE, status));

        message = messageDomainService.createMessage(1L, MessageCategory.CHAT, 100, 1, "你好");
        Assertions.assertFalse(specification.isSatisfiedBy(message));
        specification.notSatisfiedHandleBy(message,status -> Assertions.assertEquals(MessageStatus.IN_BLOCKED_LIST, status));
    }

}
