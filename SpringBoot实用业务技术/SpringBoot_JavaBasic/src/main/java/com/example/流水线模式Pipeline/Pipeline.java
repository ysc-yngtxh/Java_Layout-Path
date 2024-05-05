package com.example.流水线模式Pipeline;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: 管道
 * @date 2022/11/30 15:41
 */
public interface Pipeline {

    /**
     * 启动管道
     */
    void start();

    /**
     * 得到context
     */
    PipelineContext getContext();

    /**
     * 添加handler到队首
     */
    void addHead(Handler ... handlers);

    /**
     * 添加handler到队尾
     */
    void addTail(Handler ... handlers);
}