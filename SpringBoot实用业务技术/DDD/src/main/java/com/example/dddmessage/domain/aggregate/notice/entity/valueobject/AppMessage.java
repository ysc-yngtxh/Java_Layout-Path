package com.example.dddmessage.domain.aggregate.notice.entity.valueobject;

import com.example.dddmessage.domain.shared.enums.Application;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 移动端 APP 消息
 *
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Data
@Slf4j
@NoArgsConstructor
public class AppMessage {

	private Application application;
	private String deviceToken;
	private String title;
	private String body;
	private Map<String, String> attachData;

	public AppMessage(Application application, String deviceToken, String title, String body) {
		this.application = application;
		this.deviceToken = deviceToken;
		this.title = title;
		this.body = body;
	}
}
