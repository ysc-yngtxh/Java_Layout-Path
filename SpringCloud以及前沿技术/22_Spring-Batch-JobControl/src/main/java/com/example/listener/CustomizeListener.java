package com.example.listener;

import com.example.constant.ResourceCount;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-01 22:45
 * @apiNote TODO 自定义监听器
 */
public class CustomizeListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        StepExecutionListener.super.beforeStep(stepExecution);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // 不满足
        if (ResourceCount.totalCount != ResourceCount.readCount) {
            return ExitStatus.STOPPED;
        }
        return stepExecution.getExitStatus();
    }
}
