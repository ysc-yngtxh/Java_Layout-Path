package com.example.listener;

import com.example.event.TestEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-24 13:29
 * @apiNote TODO 事件监听
 */
@Component
public class CustomEventListener implements ApplicationListener<TestEvent<?>> {
    @Override
    public void onApplicationEvent(TestEvent event) {
        System.out.println("CustomEventListener监听到事件：" + event.getMessage());
    }

    // 支持监听异步
    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
