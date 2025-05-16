package com.example.dddmessage.infrastructure.persistence.memory;

import com.example.dddmessage.domain.aggregate.notice.entity.Notice;
import com.example.dddmessage.domain.aggregate.notice.repository.NoticeRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Repository
public class InMemoryNoticeRepository implements NoticeRepository {

	private final Map<Long, Notice> noticeRepositories = new ConcurrentHashMap<>();

	@Override
	public void save(Notice notice) {
		noticeRepositories.put(notice.getMessageId(), notice);
	}
}
