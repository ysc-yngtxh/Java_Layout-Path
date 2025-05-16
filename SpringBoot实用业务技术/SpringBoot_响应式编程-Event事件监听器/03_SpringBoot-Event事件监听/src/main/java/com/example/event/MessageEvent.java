package com.example.event;

import java.io.Serial;
import java.util.concurrent.CountDownLatch;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-01 22:50:00
 * @apiNote TODO
 */
@Setter
@Getter
public class MessageEvent extends ApplicationEvent {
	@Serial
	private static final long serialVersionUID = 3997212674591540223L;

	private String message;
	private int code;
	private CountDownLatch countDownLatch;

	public MessageEvent(Object source, String message, int code, CountDownLatch countDownLatch) {
		super(source);
		this.message = message;
		this.code = code;
		this.countDownLatch = countDownLatch;
	}
}
