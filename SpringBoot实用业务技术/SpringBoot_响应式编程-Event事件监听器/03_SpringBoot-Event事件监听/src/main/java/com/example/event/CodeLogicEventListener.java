package com.example.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-24 13:29
 * @apiNote TODO 事件监听
 */
@Component
public class CodeLogicEventListener implements ApplicationListener<CodeLogicEvent<?>> {
    // 在web 项目中（SpringBoot），系统会存在两个容器，
    // 一个是root application context,另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）
    // 这种情况下，就会造成 onApplicationEvent 方法被执行两次。
    @Override
    public void onApplicationEvent(CodeLogicEvent event) {
        System.out.println("CodeEventListener监听到事件：" + event.getMessage());
    }

    // 支持监听异步
    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
