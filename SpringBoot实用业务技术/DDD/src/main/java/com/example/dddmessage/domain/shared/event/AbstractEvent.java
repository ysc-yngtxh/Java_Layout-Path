package com.example.dddmessage.domain.shared.event;

import com.alibaba.fastjson.JSON;
import lombok.Getter;

import java.util.Date;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Getter
public abstract class AbstractEvent<T> implements DomainEvent {
    private final String id;
    private final Date timestamp;
    private final T data;

    public AbstractEvent(T data) {
        this.id = EventIdProducer.getInstance().generateId();
        this.timestamp = new Date();
        this.data = data;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public Date timestamp() {
        return this.timestamp;
    }

    @Override
    public boolean sameEventAs(DomainEvent other) {
        return this.id().equals(other.id());
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
