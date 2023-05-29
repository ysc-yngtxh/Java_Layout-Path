package com.example.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-28 22:31
 * @apiNote TODO 基于接口的自定义作业监听器
 */
public class CustomInterfaceJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {

        System.err.println("作业执行前的状态" + jobExecution.getStatus());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.err.println("作业执行后的状态" + jobExecution.getStatus());
    }
}
