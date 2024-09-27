package com.example.controller;

import com.example.config.ScheduleCronConfig;
import com.example.config.ScheduleTimerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-27 22:53
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
     *        天(星期)(1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT)  年份(1970－2099)
     * 例子：0/10 * * * * ? 每10秒触发一次
     *      10 * * * * ?   每分钟的第 10秒触发一次
     */
    @RequestMapping("/task")
    public String task(@RequestParam String cron) {
        System.out.println("new cron: " + cron);
        scheduleCronConfig.setCron(cron);
        return "ok";
    }

    @RequestMapping("/task2")
    public String task2(@RequestParam long timer) {
        System.out.println("new timer: " + timer);
        scheduleTimerConfig.setTimer(timer);
        return "ok";
    }

    @Scheduled(cron = "3 * * * * ?")
    @RequestMapping("/task3")
    public String task3() {
        System.out.println("注解式定时接口任务开启");
        return "ok";
    }

}
