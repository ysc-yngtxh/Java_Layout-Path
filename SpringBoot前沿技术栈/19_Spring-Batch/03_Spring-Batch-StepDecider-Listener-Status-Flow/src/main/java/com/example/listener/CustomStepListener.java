package com.example.listener;

import lombok.NonNull;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-30 19:49
 * @apiNote TODO 步骤监听器
 */
// @SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class CustomStepListener implements StepExecutionListener{
    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        System.out.println("-------------beforeStep------------");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("-------------afterStep------------");
        return stepExecution.getExitStatus();
    }
}
