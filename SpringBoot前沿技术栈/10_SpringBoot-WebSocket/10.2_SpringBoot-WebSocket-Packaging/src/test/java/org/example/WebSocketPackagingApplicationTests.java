package org.example;

import org.example.service.SocketService;
import org.example.websocket.WebSocketHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.mock;

@SpringBootTest
class WebSocketPackagingApplicationTests {

	@Autowired
	private WebSocketHandler handler;
	@Autowired
	private SocketService socketService;

	@Test
	public void testHandleTextMessage() throws Exception {
		WebSocketSession session = mock(WebSocketSession.class);
		TextMessage message = new TextMessage("Test message");
		handler.handleTextMessage(session, message);
	}

	// @Test
	// public void testHandleTextMessage2() throws Exception {
	// 	socketService.updateOrderStatus("123", "456", "已完成");
	// }

}
