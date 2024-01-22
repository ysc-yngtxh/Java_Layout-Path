package com.example.test1;

import java.util.List;
import java.util.concurrent.Flow.Publisher;

public class Main {
    // TODO 发布-订阅者模式
    //  (Publisher发布者发布 一个或多个Subscriber订阅者消费,每个订阅者被Subscription管理)的流式控制组件
    public static void main(String[] args) {
        // 创建一个 Publisher 发布者对象
        Publisher<List<String>> publisher = new MyPublisher<>(List.of("A报社", "B报社", "C报社"));
        
        // 可创建多个 Subscriber 订阅者对象进行消费，只需要实现其方法重写对应逻辑即可
        MySubscriber<List<String>> subscriber = new MySubscriber<>();

        // 发布者执行其订阅方法
        publisher.subscribe(subscriber);
    }
}