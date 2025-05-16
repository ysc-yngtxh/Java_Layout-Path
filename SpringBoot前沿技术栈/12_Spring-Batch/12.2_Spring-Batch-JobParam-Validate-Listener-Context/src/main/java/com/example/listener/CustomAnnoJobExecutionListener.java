package com.example.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-28 23:00
 * @apiNote TODO 基于注解形式的自定义工作监听器
 */
public class CustomAnnoJobExecutionListener {

	@BeforeJob
	public void beforeJob(JobExecution jobExecution) {
		System.err.println("作业执行前的状态" + jobExecution.getStatus());
	}

	@AfterJob
	public void afterJob(JobExecution jobExecution) {
		System.err.println("作业执行后的状态" + jobExecution.getStatus());
	}
}
