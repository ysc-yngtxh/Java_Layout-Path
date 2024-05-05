package P16_JUC并发.JUC并发Ⅵ_线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class 线程池3_CPU密集型和IO密集型 {
    /**
     * CPU密集型，IO密集型(调优)
     * 池的最大大小(线程)到底该如何设置
     *    1、CPU密集型，几核，就是几，可以保持CPU的效率最高
     *    2、IO密集型 > 判断你程序中十分耗IO的线程
     */
    public static void main(String[] args) {
        // 获取当前用户CPU的核数
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println( availableProcessors );

        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors() * 10,  // 根据用户的CPU核数来设置核心线程池大小
                Runtime.getRuntime().availableProcessors() * 30,  // 根据用户的CPU核数来设置最大核心线程池大小
                3,                                   // 超时时间,超过核心线程的线程在此时间后会被释放
                TimeUnit.SECONDS,                    // 超时单位
                new LinkedBlockingDeque<>(3),        // 阻塞队列
                Executors.defaultThreadFactory(),    // 线程工厂。创建线程的，一般不动
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略
        );

        try {
            for (int i = 1; i <= availableProcessors*10/2; i++) { // 最大任务数 = 阻塞队列数 + 最大线程池大小
                threadPoolExecutor.execute(() -> {
                    // 运行可以发现同步线程数最大值是5
                    System.out.println(Thread.currentThread().getName()+"--OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
