package com.example.dddmessage.test.domain.aggregate.message.valueobject;

import com.example.dddmessage.domain.aggregate.message.entity.valueobject.Content;
import com.example.dddmessage.domain.shared.enums.MessageCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContentTest {

	@Test
	public void test() {
		Content content = new Content(MessageCategory.CHAT, "xxx");
		Assertions.assertEquals(Content.Tpl.TEXT, content.getTpl());

		content = new Content(MessageCategory.CHAT, "http://www.baidu.com");
		Assertions.assertEquals(Content.Tpl.LINK, content.getTpl());

		content = new Content(MessageCategory.CREATED_ORDER, "{\"orderId\":1}");
		Assertions.assertEquals(Content.Tpl.CREATED_ORDER, content.getTpl());

		content = new Content(MessageCategory.PAID, "{\"orderId\":1}");
		Assertions.assertEquals(Content.Tpl.PAID, content.getTpl());
	}
}
