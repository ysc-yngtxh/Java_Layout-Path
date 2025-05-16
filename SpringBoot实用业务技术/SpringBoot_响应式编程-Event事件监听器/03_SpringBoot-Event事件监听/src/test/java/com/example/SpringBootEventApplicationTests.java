package com.example;

import com.example.event.AnnotationEvent;
import com.example.event.CodeLogicEvent;
import com.example.service.MessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootEventApplicationTests {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	@Autowired
	private MessageServiceImpl messageServiceImpl;

	@Test
	void contextLoads() {
		for (int i = 0; i < 5; i++) {
			applicationEventPublisher.publishEvent(
					new AnnotationEvent<>("你若为我繁华，你好呀：" + (i + 1))
			                                      );
		}
	}

	@Test
	void contextLoads1() {
		for (int i = 0; i < 5; i++) {
			applicationEventPublisher.publishEvent(
					new CodeLogicEvent<>("你若为我繁华，你好呀：" + (i + 1))
			                                      );
		}
	}

	@Test
	void contextLoads2() {
		messageServiceImpl.publish();
	}

}
