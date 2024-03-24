package com.example.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

/**
 * 事件
 * @param <T>
 */
@Getter
@Setter
public class TestEvent<T> extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = -3237240975748896862L;

    private T message;

    public TestEvent(T message) {
        super(message);
        this.message = message;
    }

}
