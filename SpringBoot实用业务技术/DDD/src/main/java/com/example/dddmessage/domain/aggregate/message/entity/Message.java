package com.example.dddmessage.domain.aggregate.message.entity;

import com.google.common.base.Preconditions;
import com.example.dddmessage.domain.aggregate.message.entity.valueobject.Content;
import com.example.dddmessage.domain.aggregate.message.entity.valueobject.User;
import com.example.dddmessage.domain.aggregate.message.specification.MessageStatusSpecification;
import com.example.dddmessage.domain.aggregate.message.specification.ReadSpecification;
import com.example.dddmessage.domain.shared.Entity;
import com.example.dddmessage.domain.shared.enums.MessageCategory;
import com.example.dddmessage.domain.shared.enums.MessageStatus;
import lombok.Getter;
import java.util.Date;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Getter
public class Message implements Entity<Message> {

	private final long messageId;
	private final MessageCategory category;
	private final User sender;
	private final User receiver;
	private final Content content;
	private final Date sendTime;
	private boolean senderDeleted;
	private boolean receiverDeleted;
	private Date senderDeleteTime;
	private Date receiverDeleteTime;
	private MessageStatus status;
	private boolean read;

	public Message(long messageId, MessageCategory category, User sender, User receiver, Content content, Date sendTime) {
		Preconditions.checkNotNull(category);
		Preconditions.checkNotNull(sender);
		Preconditions.checkNotNull(receiver);
		Preconditions.checkNotNull(content);
		Preconditions.checkNotNull(sendTime);
		Preconditions.checkArgument(messageId > 0);
		Preconditions.checkArgument(sender.getUserId() > 0);
		Preconditions.checkArgument(receiver.getUserId() > 0);
		Preconditions.checkArgument(content.getContent().length() > 0);
		Preconditions.checkArgument(sender.getUserId() != receiver.getUserId(), "不能自己给自己发消息");
		this.messageId = messageId;
		this.category = category;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.sendTime = sendTime;
	}

	/**
	 * 发送者删除
	 *
	 * @return
	 */
	public void senderDelete() {
		this.senderDeleted = true;
		this.senderDeleteTime = new Date();
	}

	/**
	 * 接受者删除
	 *
	 * @return
	 */
	public void receiverDelete() {
		this.receiverDeleted = true;
		this.receiverDeleteTime = new Date();
	}

	/**
	 * 撤回消息
	 *
	 * @return
	 */
	public void recall() {
		Date deletedTime = new Date();
		this.senderDeleted = true;
		this.senderDeleteTime = deletedTime;
		this.receiverDeleted = true;
		this.receiverDeleteTime = deletedTime;
		this.status = MessageStatus.RECALL;
	}

	/**
	 * 根据 specifications 来处理消息的状态
	 * 比如，发送者权限处理,违禁词处理等
	 *
	 * @return
	 */
	public void handleStatusBy(MessageStatusSpecification specification) {
		if (MessageStatus.NORMAL.sameValueAs(this.status)) {
			return;
		}
		this.status = MessageStatus.NORMAL;
		specification.notSatisfiedHandleBy(this, messageStatus -> this.status = messageStatus);
	}

	/**
	 * 根据 {@link ReadSpecification} 处理消息未读状态
	 *
	 * @param readSpecification
	 * @return
	 */
	public void handleReadStatusBy(ReadSpecification readSpecification) {
		this.read = false;
		if (readSpecification.isSatisfiedBy(this)) {
			this.read = true;
		}
	}


	@Override
	public boolean sameIdentityAs(Message other) {
		return other != null && other.getMessageId() == this.getMessageId();
	}

	@Override
	public String toString() {
		return "Message{" +
				"messageId=" + messageId +
				", sender=" + sender +
				", receiver=" + receiver +
				", content='" + content + '\'' +
				", sendTime=" + sendTime +
				", senderDeleted=" + senderDeleted +
				", receiverDeleted=" + receiverDeleted +
				", senderDeleteTime=" + senderDeleteTime +
				", receiverDeleteTime=" + receiverDeleteTime +
				", status=" + status +
				", read=" + read +
				'}';
	}
}
