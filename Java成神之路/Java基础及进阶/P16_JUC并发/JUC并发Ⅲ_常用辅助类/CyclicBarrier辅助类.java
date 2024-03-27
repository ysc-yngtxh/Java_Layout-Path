package P16_JUC并发.JUC并发Ⅲ_常用辅助类;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrier辅助类 {
    /**
     * CyclicBarrier：加法计数器
     */
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功！"); // 只有阻塞线程数达到了7个才会执行
        });

        for (int i = 1; i < 8; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " -- 集齐" + temp + "星龙珠");
                try {
                    cyclicBarrier.await(); // 阻塞当前线程，并且计数器+1。等待集齐七颗龙珠
                    System.out.println("在执行await()方法后会被阻塞吗？");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("我要许愿！世界和平！！！");
    }
}