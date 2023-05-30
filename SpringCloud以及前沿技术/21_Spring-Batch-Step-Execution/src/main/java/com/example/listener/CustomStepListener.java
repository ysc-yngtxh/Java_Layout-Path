package com.example.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-30 19:49
 * @apiNote TODO 步骤监听器
 */
public class CustomStepListener implements StepExecutionListener{
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("-------------beforeStep------------");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("-------------afterStep------------");
        return stepExecution.getExitStatus();
    }
}
