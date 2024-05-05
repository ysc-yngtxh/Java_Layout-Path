package com.example.test2;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-22 18:11
 * @apiNote TODO 官方JDK自带的发布者实现类 SubmissionPublisher
 */
public class FlowApiTest {
    public static void main(String[] args) throws InterruptedException {
        // 1. JDK 9自带的 SubmissionPublisher类，实现了发布者Publisher和链接订阅者关系Subscription功能
        //    并且Subscription功能实现的BufferedSubscription类默认使用的是FJ线程池，
        //    当我们提交完成后submit 方法结束后，任务就给到了 FJ线程池(默认线程池) 或者 自己的线程池 里执行。
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        // SubmissionPublisher<String> publisher1 =
        //         new SubmissionPublisher<>(Executors.newFixedThreadPool(1), Flow.defaultBufferSize());
        // 2. 创建一个订阅者，用于接收发布者的消息
        Subscriber<String> subscriber = new Subscriber<>() {
            private Subscription subscription;
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe 开始订阅");
                // 通过 Subscription 和发布者保持订阅关系，并用它来给发布者反馈
                this.subscription = subscription;
                // 请求一个数据
                this.subscription.request(2);
            }
            @Override
            public void onNext(String item) {
                // 接收发布者发布的消息
                System.out.println("【订阅者】接收消息 <------ " + item);
                // 接收后再次请求一个数据
                this.subscription.request(1);
                // 如果不想再接收数据，也可以直接调用 cancel，表示不再接收了
                // this.subscription.cancel();
            }
            @Override
            public void onError(Throwable throwable) {
                // 过程中出现异常会回调这个方法
                System.out.println("【订阅者】数据接收出现异常，" + throwable);
                // 出现异常，取消订阅，告诉发布者我不再接收数据了
                // 实际测试发现，只要订阅者接收消息出现异常，进入了这个回调
                // 订阅者就不会再继续接收消息了
                this.subscription.cancel();
            }
            @Override
            public void onComplete() {
                // 当发布者发出的数据都被接收了，
                // 并且发布者关闭后，会回调这个方法
                System.out.println("【订阅者】数据接收完毕");
            }
        };
        // 3. 发布者和订阅者需要建立关系
        publisher.subscribe(subscriber);

        // 4. 发布者开始发布数据
        for (int i = 0; i < 10; i++) {
            String message = "hello flow api " + i;
            System.out.println("【发布者】发布消息 ------> " + message);
            // 提交数据到发布者，并通过异步调用其onNext方法，将给定项目发布到每个当前订阅者
            publisher.submit(message);
        }

        // 5. 发布结束后，关闭发布者。除非已经关闭，否则会向当前订阅者发出onComplete信号，并禁止后续尝试发布
        publisher.close();

        // main线程延迟关闭，不然订阅者还没接收完消息，线程就被关闭了
        Thread.currentThread().join(2000);
    }
}
