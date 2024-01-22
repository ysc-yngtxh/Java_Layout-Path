package com.example.test1;

import java.util.concurrent.Flow;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-22 17:45
 * @apiNote TODO 自定义的发布者实现类
 */
public class MyPublisher<T> implements Flow.Publisher<T> {
    private T item;

    public MyPublisher(T item) {
        this.item = item;
    }

    @Override
    public void subscribe(Flow.Subscriber subscriber) {

        // 创建 Subscription 订阅者关系的对象
        Flow.Subscription subscription = new MySubscription<T>(item, subscriber);

        // 调用subscribe()方法来为发布者和订阅者建立关系
        subscriber.onSubscribe( subscription );
    }
}
