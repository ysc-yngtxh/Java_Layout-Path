package com.example.dddmessage.domain.shared.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Slf4j
@Component
public class DomainEventPublisher {

	private final EventBus eventBus;
	private String topic;

	public DomainEventPublisher() {
		this.eventBus = new EventBus(new MySubscriberExceptionHandler());
	}

	/**
	 * 注册
	 */
	public void register(Object object) {
		eventBus.register(object);
	}

	/**
	 * 发布事件
	 * 将事件发布在进程内
	 *
	 * @param event
	 */
	public void emit(DomainEvent event) {
		eventBus.post(event);
	}

	/**
	 * 直接抛出异常
	 */
	private static class MySubscriberExceptionHandler implements SubscriberExceptionHandler {
		private static String message(SubscriberExceptionContext context) {
			Method method = context.getSubscriberMethod();
			String var2 = method.getName();
			String var3 = method.getParameterTypes()[0].getName();
			String var4 = String.valueOf(context.getSubscriber());
			String var5 = String.valueOf(context.getEvent());
			return (new StringBuilder(80 + var2.length() + var3.length() + var4.length() + var5.length())).append("Exception thrown by subscriber method ").append(var2).append('(').append(var3).append(')').append(" on subscriber ").append(var4).append(" when dispatching event: ").append(var5).toString();
		}

		@Override
		public void handleException(Throwable exception, SubscriberExceptionContext context) {
			throw new RuntimeException(message(context), exception);
		}
	}
}
