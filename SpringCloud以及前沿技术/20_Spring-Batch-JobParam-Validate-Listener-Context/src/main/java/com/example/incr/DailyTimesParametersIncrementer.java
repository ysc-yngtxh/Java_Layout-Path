package com.example.incr;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.util.Date;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-27 20:10
 * @apiNote TODO 自定义作业增量参数--时间戳
 */
public class DailyTimesParametersIncrementer implements JobParametersIncrementer {
    @Override
    public JobParameters getNext(JobParameters parameters) {
        return new JobParametersBuilder(parameters)
                .addLong("daily", new Date().getTime())
                .toJobParameters();
    }
}
