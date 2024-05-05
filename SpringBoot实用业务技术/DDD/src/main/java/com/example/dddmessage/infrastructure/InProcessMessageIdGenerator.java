package com.example.dddmessage.infrastructure;

import com.example.dddmessage.domain.aggregate.message.MessageIdGenerator;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 基于进程的消息ID生成器
 *
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Component
public class InProcessMessageIdGenerator implements MessageIdGenerator {
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public long generate() {
        return this.idGenerator.getAndIncrement();
    }
}
