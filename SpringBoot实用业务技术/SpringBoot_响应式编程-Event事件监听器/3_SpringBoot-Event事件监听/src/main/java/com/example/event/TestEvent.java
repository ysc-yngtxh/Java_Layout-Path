package com.example.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
@SuppressWarnings("serial")
public class TestEvent<T> extends ApplicationEvent {
    private T message;

    public TestEvent(T message) {
        super(message);
        this.message = message;
    }

}
