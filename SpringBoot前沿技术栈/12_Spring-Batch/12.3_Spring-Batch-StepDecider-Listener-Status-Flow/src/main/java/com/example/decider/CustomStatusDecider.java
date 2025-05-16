package com.example.decider;

import jakarta.validation.constraints.NotNull;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import java.util.Random;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-01 11:30
 * @apiNote TODO 指定step返回状态值决策器
 */
// @SuppressWarnings("NullableProblems")  用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class CustomStatusDecider implements JobExecutionDecider {

	@Override
	public @NotNull FlowExecutionStatus decide(@NotNull JobExecution jobExecution, StepExecution stepExecution) {

		long ret = new Random().nextInt(3);

		if (ret == 0) {
			return new FlowExecutionStatus("A");
		} else if (ret == 1) {
			return new FlowExecutionStatus("B");
		} else {
			return new FlowExecutionStatus("C");
		}

	}
}
