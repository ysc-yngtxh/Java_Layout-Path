package com.example.流水线模式Pipeline;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2023/1/11 22:57
 */
public class PipelineMain {

    public static void main(String[] args) {
        Handler1 handler1 = new Handler1();
        Handler2 handler2 = new Handler2();
        Handler3 handler3 = new Handler3();

        OrderPipeline orderPipeline = new OrderPipeline(new PipelineContext("YouShiCheng"));
        orderPipeline.addHead(handler1, handler2);
        orderPipeline.addHead(handler3);
        orderPipeline.start();

        System.out.println("===============================");

        orderPipeline.addTail(handler1, handler2, handler3);
        orderPipeline.start();
    }
}
