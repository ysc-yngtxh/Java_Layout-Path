package com.example.test;

public class Main {
    // TODO 发布-订阅者模式(观察者模式)
    //      Publisher发布者发布 一个或多个Subscriber主题，每个主题被Subscription订阅的流式控制组件
    public static void main(String[] args) throws Exception {
        // 创建一个 Publisher 发布者对象
        SimplePublisher<Object> publisher = new SimplePublisher<>();

        // 可创建多个 Subscriber 订阅者对象进行消费，只需要实现其方法重写对应逻辑即可
        SimpleSubscriber<Object> subscriber = new SimpleSubscriber<>();

        // 发布者执行其订阅方法
        publisher.subscribe(subscriber);

        // 发布消息
        // publisher.submit("订阅你好1！！！👋");
        // publisher.submit("订阅你好2！！！👋");
        // publisher.submit("订阅你好3！！！👋");

        // 关闭发布者
        // publisher.close();

        // main线程延迟关闭，不然订阅者还没接收完消息，线程就被关闭了
        Thread.currentThread().join(2000);
    }
}