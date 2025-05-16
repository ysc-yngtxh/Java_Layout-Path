package com.example.trigger;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 22:00
 * @apiNote TODO 自定义触发器
 */
public class CustomTrigger implements Trigger {

	private final AtomicLong intervalMillis;

	public CustomTrigger(long intervalMillis) {
		this.intervalMillis = new AtomicLong(intervalMillis);
	}

	@Override
	public Instant nextExecution(TriggerContext triggerContext) {
		Instant lastExecution = triggerContext.lastActualExecution();
		return (lastExecution != null)
				? lastExecution.plusMillis(intervalMillis.get())
				: Instant.now().plusMillis(intervalMillis.get());
	}

	// 动态修改间隔时间
	public void setInterval(long intervalMillis) {
		this.intervalMillis.set(intervalMillis);
	}
}
