package com.example.controller;

import com.example.config.ScheduleCronConfig;
import com.example.config.ScheduleTimerConfig;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 23:00
 * @apiNote TODO
 */
@RestController
public class TaskController {

	@Autowired
	private ScheduleCronConfig scheduleCronConfig;

	@Autowired
	private ScheduleTimerConfig scheduleTimerConfig;

	/**
	 * cron：一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。
	 * 按顺序：秒(0~59)  分钟(0~59)  小时(0~23)  天(月)(0~31，但是你需要考虑你月的天数)  月(0~11)
	 * 天(星期)(1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT)  年份(1970－2099)
	 * 例子：0/10 * * * * ? 每10秒触发一次
	 * 10 * * * * ?   每分钟的第 10秒触发一次
	 */
	// 注解式定时任务
	@Scheduled(cron = "3 * * * * ?")
	@RequestMapping("/task")
	public String task() {
		System.out.println("注解式定时接口任务开启");
		return "ok";
	}

	// 配置类定时任务 -> 写法一
	@RequestMapping("/task1")
	public String task1(@RequestParam String cron) {
		System.out.println("new cron: " + cron);
		scheduleCronConfig.setCron(cron);
		return "ok";
	}

	// 配置类定时任务 -> 写法二
	@RequestMapping("/task2")
	public String task2(@RequestParam long timer) {
		System.out.println("new timer: " + timer);
		scheduleTimerConfig.setTimer(timer);
		return "ok";
	}

	// 线程池支持定时任务
	@RequestMapping("/task3")
	public String task3() {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		try {
			ScheduledFuture<String> scheduledFuture = executorService.schedule(
					() -> {
						return "call";
					}
					, 10
					, TimeUnit.SECONDS
			);
			System.out.println(scheduledFuture.get());

			ScheduledFuture<?> scheduledFuture2 = executorService.scheduleAtFixedRate(
					() -> System.out.println("原生定时任务执行时间：" + LocalDateTime.now())
					, 0
					, 5000
					, TimeUnit.MILLISECONDS
			);
			System.out.println(scheduledFuture2.get());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			executorService.shutdown();
		}
		return "ok";
	}

	// JDK原生Timer类支持定时任务
	@RequestMapping("/task4")
	public String task4() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("定时任务开启");
			}
		}, 3000);
		return "ok";
	}
}
