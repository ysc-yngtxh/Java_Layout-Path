package com.example.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-01 23:11
 * @apiNote TODO
 */
@Getter
public class CodeLogicEvent<T> extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = -7520845475992747736L;

    T message;

    public CodeLogicEvent(T message) {
        super(message);
        this.message = message;
    }
}
