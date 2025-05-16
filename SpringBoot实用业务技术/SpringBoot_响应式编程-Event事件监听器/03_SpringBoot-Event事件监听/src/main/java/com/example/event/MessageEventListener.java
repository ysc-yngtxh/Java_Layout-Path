package com.example.event;

import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-01 23:00:00
 * @apiNote TODO
 */
@Component
public class MessageEventListener implements ApplicationListener<MessageEvent> {

	@SneakyThrows
	public void onApplicationEvent(MessageEvent event) {
		System.out.println("被监听到了。。。。。");
		TimeUnit.SECONDS.sleep(5);
		event.getCountDownLatch().countDown();
	}
}
