package P16_JUC并发.JUC并发Ⅲ_常用辅助类;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Semaphore辅助类 {
    /** Semaphore：信号机
     *   常用方法：
     *      acquire()  获取一个令牌，在获取到令牌、或者被其他线程调用中断之前线程一直处于阻塞状态。（假设如果已经满了，等待，等待被释放为止）
     *      acquire(int permits)  获取一个令牌，在获取到令牌、或者被其他线程调用中断、或超时之前线程一直处于阻塞状态。
     *      tryAcquire()  尝试获得令牌，返回获取令牌成功或失败，不阻塞线程。
     *      tryAcquire(long timeout, TimeUnit unit) 尝试获得令牌，在超时时间内循环尝试获取，直到尝试获取成功或超时返回，不阻塞线程。
     *      release() 释放一个令牌，唤醒一个获取令牌不成功的阻塞线程。（会将当前的信号量释放，然后唤醒等待的线程。）
     *   不常用方法：
     *      hasQueuedThreads() 等待队列里是否还存在等待线程。
     *      getQueueLength() 获取等待队列里阻塞的线程数。
     *      drainPermits() 清空令牌把可用令牌数置为0，返回清空令牌的数量。
     *      availablePermits() 返回可用的令牌数量。
     *   作用：
     *      多个共享资源互斥的使用！并发限流，控制最大的线程数
     */
    public static void main(String[] args) {
        // 停车场同时可以容纳3辆车
        Semaphore semaphore = new Semaphore(3);
        // 模拟10辆车进入停车场
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "来到停车场");
                        if (semaphore.availablePermits() == 0) { // 获取还剩下的停车位
                            System.err.println("车位不足，请耐心等待");
                        }
                        semaphore.acquire(); // 获取令牌进入停车场，当没有令牌会在此处等待。
                        System.out.println("==== " + Thread.currentThread().getName() + "成功进入停车场");
                        Thread.sleep(new Random().nextInt(10000)); // 模拟车辆在停车场停留的时间（10s）
                        System.out.println("🚗🚗🚗" + Thread.currentThread().getName() + "驶出停车场");
                        semaphore.release(); // 释放令牌，腾出停车场车位
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, i + "号车").start();
        }
    }
}
