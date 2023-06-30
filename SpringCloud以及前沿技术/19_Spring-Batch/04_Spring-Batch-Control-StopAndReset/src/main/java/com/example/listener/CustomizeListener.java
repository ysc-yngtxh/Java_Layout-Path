package com.example.listener;

import com.example.constant.ResourceCount;
import lombok.NonNull;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-01 22:45
 * @apiNote TODO 自定义监听器
 */
// @SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class CustomizeListener implements StepExecutionListener {
    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        StepExecutionListener.super.beforeStep(stepExecution);
    }

    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        // 不满足
        if (ResourceCount.totalCount != ResourceCount.readCount) {
            return ExitStatus.STOPPED;
        }
        return stepExecution.getExitStatus();
    }
}
