package com.example.config;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 23:00
 * @apiNote TODO
 */
@Data
@Component
public class ScheduleTimerConfig implements SchedulingConfigurer {

    private Long timer = 10000L;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                () -> {
                    System.out.println("定义毫秒的定时任务执行了：" + LocalDateTime.now());
                },
                triggerContext -> {
                    return new PeriodicTrigger(timer).nextExecution(triggerContext);
                }
        );
    }
}
