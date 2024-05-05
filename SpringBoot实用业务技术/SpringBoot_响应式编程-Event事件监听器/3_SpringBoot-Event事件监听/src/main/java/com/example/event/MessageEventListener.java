package com.example.event;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-01 22:56
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
