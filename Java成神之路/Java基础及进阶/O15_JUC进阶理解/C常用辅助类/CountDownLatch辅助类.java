package O15_JUC进阶理解.C常用辅助类;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch辅助类 {
    /**
     * CountDownLatch:允许一个或多个线程等待直到在其它线程中执行的一组操作完成的同步辅助(相当于一个计数器)
     *    原理：count.countDown(); // 数量-1
     *         count.await();     // 等待计数器归零，然后再向下执行
     *         每次有线程调用countDown()数量-1，假设计数器变为0，count.await()就会被唤醒，继续执行。
     */
    public static void main(String[] args) throws InterruptedException {
        //总数是6
        CountDownLatch count = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "--Go Out");
                count.countDown(); // 线程数-1
            }, String.valueOf(i)).start();
        }

        count.await(); // 等待计数器归零，然后再向下执行
        System.out.println("Close Door");
    }
}
