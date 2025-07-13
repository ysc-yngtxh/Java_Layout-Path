package org.example.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping("/api/socket")
public class SystemController {

	@MessageMapping("/send")
	@SendTo("/topic/messages")
	public Message sendMessage(Message message) {
		return new Message("Hello, WebSocket!", "System");
	}

}
