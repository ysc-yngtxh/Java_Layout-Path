package com.example.incr;

import lombok.NonNull;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.util.Date;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-27 20:10
 * @apiNote TODO 自定义作业增量参数--时间戳
 */
// @SuppressWarnings("NullableProblems")  用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class DailyTimesParametersIncrementer implements JobParametersIncrementer {

	@Override
	public @NonNull JobParameters getNext(JobParameters parameters) {
		return new JobParametersBuilder(parameters)
				.addLong("daily", new Date().getTime())
				.toJobParameters();
	}
}
