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
 * @dateTime 2024-09-27 22:00
 * @apiNote TODO
 */
@Data
@Component
// @PropertySource 是 Spring 框架提供的一个注解，主要用于加载指定的属性文件到 Spring 的 Environment 环境中。
@PropertySource("classpath:time-config.ini")
public class ScheduleCronConfig implements SchedulingConfigurer {

	@Value("${printTime.cron}")
	private String cron;

	// 根据 cron 表达式来设置定时任务
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(
				() -> {
					System.out.println("cron表达式的定时任务执行了：" + LocalDateTime.now());
				},
				triggerContext -> {
					CronTrigger cronTrigger = new CronTrigger(cron);
					return cronTrigger.nextExecution(triggerContext);
				}
		);
	}
}
