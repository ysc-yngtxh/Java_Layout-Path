package com.example.dddmessage.test.domain.aggregate.message.specification;

import com.example.dddmessage.domain.aggregate.message.entity.Message;
import com.example.dddmessage.domain.aggregate.message.entity.valueobject.Content;
import com.example.dddmessage.domain.aggregate.message.entity.valueobject.User;
import com.example.dddmessage.domain.aggregate.message.specification.RecallSpecification;
import com.example.dddmessage.domain.shared.enums.MessageCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class RecallSpecificationTest {

	@Autowired
	private RecallSpecification recallSpecification;

	@Test
	public void isSatisfiedByTest() {
		Message message1 = new Message(
				1L,
				MessageCategory.CHAT,
				new User(1, "", ""),
				new User(2, "", ""),
				new Content(MessageCategory.CHAT, "xxx"),
				new Date()
		);
		Assertions.assertTrue(recallSpecification.isSatisfiedBy(message1));
		Message message2 = new Message(
				1L,
				MessageCategory.CHAT,
				new User(1, "", ""),
				new User(2, "", ""),
				new Content(MessageCategory.CHAT, "xxx"),
				new Date(System.currentTimeMillis() - 60 * 6 * 1000)
		);
		Assertions.assertFalse(recallSpecification.isSatisfiedBy(message2));
	}
}
