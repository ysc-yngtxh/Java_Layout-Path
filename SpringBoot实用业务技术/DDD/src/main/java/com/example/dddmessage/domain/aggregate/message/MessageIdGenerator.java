package com.example.dddmessage.domain.aggregate.message;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface MessageIdGenerator {
    /**
     * 生成全局唯一的mesageId
     *
     * @return
     */
    long generate();
}
