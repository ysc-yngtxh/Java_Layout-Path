package com.example.dddmessage.interfaces.web.controller;

import com.example.dddmessage.shared.Result;
import com.example.dddmessage.domain.aggregate.message.MessageIdGenerator;
import com.example.dddmessage.interfaces.facade.MessageServiceFacade;
import com.example.dddmessage.interfaces.facade.command.SendMessageCommand;
import com.example.dddmessage.interfaces.facade.dto.MessageDTO;
import com.example.dddmessage.interfaces.web.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@RestController
public class MessageController {

	private final MessageServiceFacade messageServiceFacade;
	private final MessageIdGenerator messageIdGenerator;

	public MessageController(MessageServiceFacade messageServiceFacade, MessageIdGenerator messageIdGenerator) {
		this.messageServiceFacade = messageServiceFacade;
		this.messageIdGenerator = messageIdGenerator;
	}

	/**
	 * 发送消息
	 *
	 * @param cmd {@link SendMessageCommand}
	 * @return 消息ID
	 */
	@PostMapping("/message")
	public Response<Long> sendMessage(@Validated @RequestBody SendMessageCommand cmd) {
		long messageId = messageIdGenerator.generate();
		cmd.setMessageId(messageId);
		messageServiceFacade.sendMessage(cmd);
		return Response.ok(messageId);
	}

	/**
	 * 撤回消息
	 *
	 * @param messageId
	 * @param request
	 * @return
	 */
	@PostMapping("/message/{messageId}/recall")
	public Response<Void> recallMessage(@PathVariable long messageId, HttpServletRequest request) {
		int userId = Integer.parseInt(request.getHeader("mock-user-id"));
		Result<Void> result = this.messageServiceFacade.recallMessage(userId, messageId);
		if (result.isSuccess()) {
			return Response.ok(result.getData());
		}
		return Response.failed(result.getError().getMsg());
	}

	@GetMapping("/unreadMessageTotal")
	public Response<Integer> getUnreadMessageTotal(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getHeader("mock-user-id"));
		return Response.ok(messageServiceFacade.getUnreadMessageTotal(userId));
	}

	@GetMapping("/contact/{contactId}/messages")
	public Response<List<MessageDTO>> getContactMessageList(@PathVariable int contactId, Integer size, HttpServletRequest request) {
		int userId = Integer.parseInt(request.getHeader("mock-user-id"));
		List<MessageDTO> data = messageServiceFacade.getContactMessageList(userId, contactId, Optional.ofNullable(size).orElse(10));
		return Response.ok(data);
	}
}
