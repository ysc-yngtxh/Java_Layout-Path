package com.example.dddmessage.domain.aggregate.notice.entity;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.example.dddmessage.domain.aggregate.message.entity.Message;
import com.example.dddmessage.domain.aggregate.message.entity.valueobject.Content;
import com.example.dddmessage.domain.aggregate.notice.entity.valueobject.AppMessage;
import com.example.dddmessage.domain.aggregate.notice.entity.valueobject.SocketMessage;
import com.example.dddmessage.domain.shared.enums.Application;
import lombok.Getter;

import java.util.*;

/**
 * 通知实体
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Getter
public class Notice {

	private final long messageId;
	private final int sender;
	private final String senderNickname;
	private final String senderPhoto;
	private final int receiver;
	private final String receiverNickname;
	private final String receiverPhoto;
	private final Content content;
	private final Date sendTime;

	private List<SocketMessage> socketMessages;
	private List<AppMessage> appMessages;

	public Notice(Message message) {
		this.messageId = message.getMessageId();
		this.sender = message.getSender().getUserId();
		this.senderNickname = message.getSender().getNickname();
		this.senderPhoto = message.getSender().getPhoto();
		this.receiver = message.getReceiver().getUserId();
		this.receiverNickname = message.getReceiver().getNickname();
		this.receiverPhoto = message.getReceiver().getPhoto();
		this.content = message.getContent();
		this.sendTime = message.getSendTime();
	}

	/**
	 * 站内socket推送消息工厂方法
	 * 依据业务每条消息，产生的站内socket通知是恒定的
	 *
	 * @return
	 */
	public List<SocketMessage> createImMessage() {
		if (Objects.nonNull(this.socketMessages)) {
			return this.socketMessages;
		}
		Map<String, Object> data = ImmutableMap.<String, Object>builder()
		                                       .put("messageId", messageId)
		                                       .put("sender", sender)
		                                       .put("senderNickname", senderNickname)
		                                       .put("senderPhoto", senderPhoto)
		                                       .put("receiver", receiver)
		                                       .put("receiverNickname", receiverNickname)
		                                       .put("receiverPhoto", receiverPhoto)
		                                       .put("content", content)
		                                       .put("sendTime", sendTime)
		                                       .build();
		this.socketMessages = Arrays.asList(
				new SocketMessage(receiver, "mock_channel", new JSONObject(data))
		                                   );
		return Collections.unmodifiableList(this.socketMessages);
	}

	/**
	 * 移动端推送消息工厂方法
	 * 依据业务每条消息，产生的移动端通知是恒定的
	 *
	 * @return
	 */
	public List<AppMessage> createAppMessages() {
		if (Objects.nonNull(this.appMessages)) {
			return this.appMessages;
		}
		String body = "";
		switch (content.getTpl()) {
			case Content.Tpl.TEXT:
				body = content.getContent();
				break;
			case Content.Tpl.LINK:
				body = "[链接]";
				break;
			case Content.Tpl.CREATED_ORDER:
				body = "[新订单]";
				break;
			case Content.Tpl.PAID:
				body = "[买家已付款]";
				break;
		}
		// 模拟用户有两个设备
		this.appMessages = Arrays.asList(
				new AppMessage(Application.ANDROID_XXX, "mock_deviceToken", senderNickname, body),
				new AppMessage(Application.IOS_XXX, "mock_deviceToken", senderNickname, body)
		                                );
		return Collections.unmodifiableList(this.appMessages);
	}

	/**
	 * 返回一个不可修改的AppMessage列表
	 *
	 * @return
	 */
	public List<AppMessage> getAppMessages() {
		return Collections.unmodifiableList(this.appMessages);
	}

	/**
	 * 返回一个不可修改的SocketMessage列表
	 *
	 * @return
	 */
	public List<SocketMessage> getSocketMessages() {
		return Collections.unmodifiableList(this.socketMessages);
	}
}
