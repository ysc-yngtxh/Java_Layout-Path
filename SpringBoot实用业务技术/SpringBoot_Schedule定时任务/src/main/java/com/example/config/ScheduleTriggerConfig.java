package com.example.config;

import com.example.trigger.CustomTrigger;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 22:00
 * @apiNote TODO
 */
@Data
@Component
public class ScheduleTriggerConfig implements SchedulingConfigurer {

	private long trigger = 5000L;

	// 根据 自定义的触发器 来设置定时任务
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(
				() -> {
					System.out.println("自定义触发器任务执行时间：" + LocalDateTime.now());
				},
				triggerContext -> {
					return new CustomTrigger(trigger).nextExecution(triggerContext);
				}
		);
	}
}
