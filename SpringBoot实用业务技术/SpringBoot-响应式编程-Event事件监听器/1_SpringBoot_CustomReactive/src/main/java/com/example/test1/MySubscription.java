package com.example.test1;

import java.util.List;
import java.util.concurrent.Flow;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-22 18:11
 * @apiNote TODO 自定义建立订阅关系的实现类
 */
public class MySubscription<T> implements Flow.Subscription {
    private T items;
    private int index = 0;
    private boolean completed = false;
    private Flow.Subscriber<T> subscriber;

    public MySubscription(T items, Flow.Subscriber<T> subscriber) {
        this.items = items;
        this.subscriber = subscriber;
    }

    // 用于向数据发布者请求n个数据项
    @Override
    public void request(long n) {
        if (n > 0 && !completed) {
            // 这里只做一个List集合数据例子
            if (items instanceof List) {
                List list = (List) items;
                if (index < list.size()) {
                    subscriber.onNext((T) list.get(index++));
                    if (index == list.size()) {
                        completed = true;
                        subscriber.onComplete();
                    }
                }
            } else {
                // 。。。。。。
            }
        } else {
            subscriber.onError(new IllegalArgumentException("Invalid request"));
        }
    }

    // 取消消息订阅，订阅者将不再接收数据
    @Override
    public void cancel() {
        completed = true;
    }
}
