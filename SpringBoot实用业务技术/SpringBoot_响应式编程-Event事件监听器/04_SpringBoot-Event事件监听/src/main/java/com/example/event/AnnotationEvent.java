package com.example.event;

import java.io.Serial;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 事件
 *
 * @param <T>
 */
@Getter
@Setter
public class AnnotationEvent<T> extends ApplicationEvent {
	@Serial
	private static final long serialVersionUID = -3237240975748896862L;

	private T message;

	public AnnotationEvent(T message) {
		super(message);
		this.message = message;
	}

}
