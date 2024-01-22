package com.example;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

class MySubscriber<T> implements Subscriber<T> {
    private Subscription subscription;
    
    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("onSubscribe");
        this.subscription = subscription;
        subscription.request(1); // 向数据源请求1个数据项
    }
    
    @Override
    public void onNext(T item) {
        System.out.println("Received item: " + item);
        subscription.request(1); // 继续请求下一个数据项
    }
    
    @Override
    public void onError(Throwable throwable) {
        System.err.println("Error occurred: " + throwable.getMessage());
    }
    
    @Override
    public void onComplete() {
        System.out.println("Data stream completed");
    }
}