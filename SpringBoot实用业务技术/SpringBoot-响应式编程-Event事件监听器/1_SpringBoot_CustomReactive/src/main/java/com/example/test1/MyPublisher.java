package com.example.test1;

import lombok.SneakyThrows;
import org.springframework.util.concurrent.FutureUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-22 17:45
 * @apiNote TODO 自定义的发布者实现类
 */
public class MyPublisher<T> implements Flow.Publisher<T> {
    // 定义FJ线程池
    private final ExecutorService executor = ForkJoinPool.commonPool(); // daemon-based
    // 是否被订阅,因为这个发布者只能被一个人订阅
    private boolean subscribed; // true after first subscribe
    // 定义接收消息的阻塞队列
    private AtomicReference<BlockingQueue<T>> queue;

    public MyPublisher() {
        queue = new AtomicReference<>(new ArrayBlockingQueue<>(Flow.defaultBufferSize()));
    }

    public MyPublisher(int capacity) {
        queue = new AtomicReference<>(new ArrayBlockingQueue<>(capacity));
    }

    public void submit(T item) {
        try {
            // 阻塞等待直到队列有空位
            queue.get().put(item);
        } catch (InterruptedException e) {
            // 中断当前线程
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    // 订阅方法
    @Override
    public synchronized void subscribe(Subscriber<? super T> subscriber) {
        if (subscribed)
            subscriber.onError(new IllegalStateException()); // only one allowed
        else {
            // 订阅成功
            subscribed = true;
            subscriber.onSubscribe(new MySubscription<>(this, subscriber, executor));
        }
    }


    // 订阅管理
    static class MySubscription<T> implements Subscription {
        // 发布者
        private final MyPublisher<T> publisher;
        // 订阅者
        private final MySubscriber<? super T> subscriber;
        // 线程池
        private final ExecutorService executor;
        // 结果
        private CompletableFuture<Void> future; // to allow cancellation
        // 是否完成
        private boolean completed;
        // 阻塞队列中元素个数
        private int size;
        // 在阻塞队列中取出的消息数
        private int count;

        // 构造方法
        MySubscription(MyPublisher<T> publisher,
                       Subscriber<? super T> subscriber,
                       ExecutorService executor) {
            this.publisher = publisher;
            this.size = publisher.queue.get().size();
            this.subscriber = (MySubscriber<? super T>) subscriber;
            this.executor = executor;
        }

        // request
        @Override
        @SneakyThrows
        public void request(long n) {
            // 阻塞等待直到队列有元素可用
            // 没有完成
            List<CompletableFuture<Void>> futures = new ArrayList<>();
            if (!completed) {
                if (n <= 0) {
                    IllegalArgumentException ex = new IllegalArgumentException();
                    executor.execute(() -> subscriber.onError(ex));
                } else {
                    for (int i = 0; i < n; i++) {
                        // 执行方法
                        future = CompletableFuture.runAsync(() -> {
                            try {
                                if (!publisher.queue.get().isEmpty()) {
                                    subscriber.onNext(publisher.queue.get().take());
                                    count++;
                                }
                            } catch (InterruptedException e) {
                                // 中断当前线程
                                Thread.currentThread().interrupt();
                                // 通知订阅者发生错误
                                subscriber.onError(e);
                            }
                        }, executor);
                        futures.add(future);
                    }

                    // toArray(new String[0]) 的写法不是为了最终返回一个容量为0的数组，
                    // 而是这样创建数组，可以确保返回的新数组会进行自动扩容，其大小与集合的大小相同
                    CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
                    allFutures.join(); // 等待所有任务完成

                    // 检查是否所有任务都已完成，并执行subscriber.onComplete()
                    if (size == count) {
                        subscriber.onComplete();
                    }
                }
            }
        }


        // 取消
        @Override
        public void cancel() {
            completed = true;
            if (future != null) future.cancel(false);
        }

    }
}
