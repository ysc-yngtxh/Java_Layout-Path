package com.example.config;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 22:42
 * @apiNote TODO
 */
@Data
@Component
@PropertySource("classpath:time-config.ini")
public class ScheduleCronConfig implements SchedulingConfigurer {

    @Value("${printTime.cron}")
    private String cron;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                () -> {
                    System.out.println("cron表达式的定时任务执行了：" + LocalDateTime.now());
                },
                triggerContext -> {
                    // 使用CronTrigger触发器，可动态修改cron表达式来操作循环规则
                    CronTrigger cronTrigger = new CronTrigger(cron);
                    return cronTrigger.nextExecutionTime(triggerContext).toInstant();
                }
        );
    }
}
