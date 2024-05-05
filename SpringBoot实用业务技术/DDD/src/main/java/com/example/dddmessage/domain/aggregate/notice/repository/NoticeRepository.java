package com.example.dddmessage.domain.aggregate.notice.repository;

import com.example.dddmessage.domain.aggregate.notice.entity.Notice;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface NoticeRepository {
    /**
     * 保存消息
     *
     * @param notice
     */
    void save(Notice notice);
}
