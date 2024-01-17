package com.example.listener;

import com.example.event.TestEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CustomEventListener {
    @Async
    @EventListener
    public void listener(TestEvent event) {
        log.info("监听到数据1：{}", event.getMessage());
    }
}
