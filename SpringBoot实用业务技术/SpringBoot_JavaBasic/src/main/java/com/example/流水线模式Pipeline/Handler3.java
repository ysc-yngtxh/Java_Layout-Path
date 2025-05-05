package com.example.流水线模式Pipeline;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2023/1/11 22:58
 */
public class Handler3 implements Handler<PipelineContext> {

    @Override
    public Boolean handler(PipelineContext context) {
        System.out.println("handler3...handler");
        return true;
    }
}
