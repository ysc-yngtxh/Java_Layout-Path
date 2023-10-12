package com.example.listener;

import lombok.NonNull;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-30 19:53
 * @apiNote TODO 居于块Chunk监听器
 */
// @SuppressWarnings("NullableProblems")  // 用于抑制警告。相当于有了这个注解就可以不用加上 @NonNull、@NotNull等
public class CustomChunkListener implements ChunkListener {
    @Override
    public void beforeChunk(@NonNull ChunkContext context) {
        System.out.println("-------------beforeStep-Chunk------------");
    }

    @Override
    public void afterChunk(@NonNull ChunkContext context) {
        System.out.println("-------------afterStep-Chunk------------");
    }

    /**
     * TODO 区别于实现的StepExecutionListener接口监听器主要在于这个方法
     *   这个方法表示当Chunk()方法执行失败后回调
     */
    @Override
    public void afterChunkError(@NonNull ChunkContext context) {
        ChunkListener.super.afterChunkError(context);
    }
}
