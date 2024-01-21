package com.example.event;

import org.springframework.context.ApplicationEvent;

public class TestEvent extends ApplicationEvent {
    private String message;

    public TestEvent(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
