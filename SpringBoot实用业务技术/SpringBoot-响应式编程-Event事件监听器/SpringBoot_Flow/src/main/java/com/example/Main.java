package com.example;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscription;

public class Main {
    public static void main(String[] args) {
        Publisher<String> publisher = subscriber -> {
            subscriber.onSubscribe(new Subscription() {
                private final String[] items = {"Item 1", "Item 2", "Item 3"};
                private int index = 0;
                private boolean completed = false;
                
                @Override
                public void request(long n) {
                    if (n > 0 && !completed) {
                        if (index < items.length) {
                            subscriber.onNext(items[index++]);
                            
                            if (index == items.length) {
                                completed = true;
                                subscriber.onComplete();
                            }
                        }
                    } else {
                        subscriber.onError(new IllegalArgumentException("Invalid request"));
                    }
                }
                
                @Override
                public void cancel() {
                    completed = true;
                }
            });
        };
        
        // 创建一个 MySubscriber 对象并订阅 Publisher
        MySubscriber<String> subscriber = new MySubscriber<>();
        publisher.subscribe(subscriber);
    }
}