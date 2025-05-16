package com.example.test3;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-22 18:00
 * @apiNote TODO Subscriber将Publisher发布的数据缓冲在Subscription中，其默认长度为256，超过长度会触发背压(Backpressure)
 *               Publisher将会停止发送数据，直到Subscriber接收了数据Subscription有空闲位置的时候，Publisher才会继续发布数据
 */
public class BPFlowTest {

    public static void main(String[] args) throws InterruptedException {
        // 1. 定义String类型的数据发布者，JDK 9自带的 SubmissionPublisher实现了 Publisher
        // 相当于同时具备Publisher和Subscription功能
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

		// 2. 创建一个订阅者，用于接收发布者的消息
		Subscriber<String> subscriber = new Subscriber<>() {

			private Subscription subscription;

			@Override
			public void onSubscribe(Subscription subscription) {
				// 通过 Subscription 和发布者保持订阅关系，并用它来给发布者反馈
				this.subscription = subscription;
				// 请求一个数据
				this.subscription.request(1);
			}

			@Override
			public void onNext(String item) {
				// 接收发布者发布的消息
				System.out.println("【订阅者】接收消息 <------ " + item);

				// 模拟接收数据缓慢，让缓冲池填满
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// 接收后再次请求一个数据，表示我已经处理完了，你可以再发数据过来了
				this.subscription.request(1);

				// 如果不想再接收数据，也可以直接调用cancel，表示不再接收了
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

		// 4. 发布者开始发布数据。数据缓冲在Subscription中，其长度默认为256。很明显现在发布的数据长度超过了256
		for (int i = 0; i < 500; i++) {
			String message = "hello flow api " + i;
			System.out.println("【发布者】发布消息 ------> " + message);
			// 提交数据到发布者，并通过异步调用其onNext方法，将给定项目发布到每个当前订阅者
			publisher.submit(message);
		}

		// 5. 发布结束后，关闭发布者。除非已经关闭，否则会向当前订阅者发出onComplete信号，并禁止后续尝试发布
		publisher.close();

		// main线程延迟关闭，不然订阅者还没接收完消息，线程就被关闭了
		Thread.currentThread().join(20000);
	}
}
