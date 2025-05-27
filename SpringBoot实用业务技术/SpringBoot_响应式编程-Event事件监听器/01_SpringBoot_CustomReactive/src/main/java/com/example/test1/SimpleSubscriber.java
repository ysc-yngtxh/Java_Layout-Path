// package com.example.test1;
//
// import java.util.concurrent.Flow;
//
// /**
//  * @author 游家纨绔
//  * @dateTime 2024-01-25 21:18
//  * @apiNote TODO
//  */
// public class SimpleSubscriber<T> implements Flow.Subscriber<T> {
//
//     // 建立订阅主题关系类
//     private SimplePublisher.SimpleSubscription<T> subscriptions;
//
//     // 订阅成功的回调方法，用于初始化Subscription，并且表明开始接受订阅数据了
//     @Override
//     public void onSubscribe(Flow.Subscription subscription) {
//         System.out.println("onSubscribe 开始订阅");
//         this.subscriptions = (SimplePublisher.SimpleSubscription<T>) subscription;
//         // 向数据源请求1个数据项
//         subscriptions.request(1);
//     }
//
//     // 接收下一项订阅数据的回调方法
//     @Override
//     public void onNext(T item) {
//         System.out.println("onNext item: " + item);
//         // 继续请求下一个数据项
//         subscriptions.request(1);
//         // subscriptions.cancel();
//     }
//
//     // 在Publisher和Subscriber遇到不可恢复的错误时调用此方法，Subscriber不再接收订阅信息
//     @Override
//     public void onError(Throwable throwable) {
//         System.err.println("Error occurred: " + throwable.getMessage());
//         // 取消消息订阅
//         subscriptions.cancel();
//     }
//
//     // 当接收完所有的订阅数据，并且发布者已经关闭后会回调此方法
//     @Override
//     public void onComplete() {
//         System.out.println("Data stream completed：订阅结束");
//     }
// }
