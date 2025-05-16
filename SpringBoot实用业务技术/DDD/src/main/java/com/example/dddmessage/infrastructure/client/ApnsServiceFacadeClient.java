package com.example.dddmessage.infrastructure.client;

import com.example.dddmessage.domain.aggregate.notice.entity.valueobject.AppMessage;
import com.example.dddmessage.domain.shared.facade.ApnsServiceFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Component
@Slf4j
public class ApnsServiceFacadeClient implements ApnsServiceFacade {

	@Override
	public void publish(AppMessage appMessage) {
		// 调用apns接口发布消息
		log.info(appMessage.toString());
	}
}
