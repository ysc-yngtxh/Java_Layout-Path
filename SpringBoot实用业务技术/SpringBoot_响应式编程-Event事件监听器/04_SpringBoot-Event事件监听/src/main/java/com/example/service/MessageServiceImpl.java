package com.example.service;

import com.example.event.MessageEvent;
import java.util.concurrent.CountDownLatch;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-01 22:50:00
 * @apiNote TODO
 */
@Service
public class MessageServiceImpl {

	@Autowired
	private ApplicationContext applicationContext;

	@SneakyThrows
	public void publish() {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		applicationContext.publishEvent(new MessageEvent(this, "Hello, World!", 200, countDownLatch));
		countDownLatch.await();
		System.out.println("监听事件执行结束！！！");
	}
}
