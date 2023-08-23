package O15_JUC进阶理解.JUC_Ⅲ_常用辅助类;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrier辅助类 {
    /**
     * CycliBarrier：加法计数器
     */
    public static void main(String[] args) {
        CyclicBarrier cycliBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功！"); // 只有线程数达到了7个才会执行
        });

        for (int i = 1; i < 8; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " -- 集齐" + temp + "星龙珠");
                try {
                    cycliBarrier.await(); // 等待集齐七颗龙珠
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println("我要许愿！世界和平！！！");
    }
}
