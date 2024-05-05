package P16_JUC并发.JUC并发Ⅵ_线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class 线程池1_池化技术 {
    /**
     * 池化技术
     *    程序的运行，本质：占用系统的资源！优化资源的使用--池化技术
     *    线程池、连接池、内存池、对象池。。。。(创建、销毁是十分浪费资源)
     *    池化技术：事先准备好一些资源，有人要用，就来我这里拿，用完之后还给我
     *
     * 线程池的好处：
     *    1、降低资源的消耗
     *    2、提高响应的速度
     *    3、方便管理
     *
     * 【强制】线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，
     *       这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
     * Executors线程池的弊端：
     *     1、默认允许的请求队列长度为Integer.MAX_VALUE(约为21亿),可能会堆积请求队列长度，去开辟内存空间，从而导致OOM(内存溢出)
     *     2、默认允许的创建线程数量为Integer.MAX_VALUE(约为21亿),可能会创建大量的线程，去开辟内存空间，从而导致OOM(内存溢出)
     */
    public static void main(String[] args) {
        // 不建议使用这种形式去创建线程池
        ExecutorService executorService1 = Executors.newSingleThreadExecutor(); // Single单例线程
        ExecutorService executorService2 = Executors.newFixedThreadPool(5);     // 固定线程，5个线程
        ExecutorService executorService3 = Executors.newCachedThreadPool();     // 可伸缩线程，随用户需要去扩展线程数
        ScheduledExecutorService executorService4 = Executors.newScheduledThreadPool(3);

        try {
            // 可以在运行后发现线程启动最多只有一个
            for (int i = 1; i <= 10; i++) {
                executorService1.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "--OK");
                });
            }

            TimeUnit.SECONDS.sleep(2);
            System.out.println("==========2==========");

            // 可以在运行后发现线程启动最多有五个
            for (int i = 1; i <= 10; i++) {
                executorService2.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "--OK");
                });
            }

            TimeUnit.SECONDS.sleep(2);
            System.out.println("==========3==========");

            // 可以在运行后发现线程启动最多有十个
            for (int i = 1; i <= 10; i++) {
                executorService3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "--OK");
                });
            }

            System.out.println("===========4=========");

            // 定时(延时)执行线程任务
            ScheduledFuture<String> schedule = executorService4.schedule(() -> {
                return "call";
            }, 10, TimeUnit.SECONDS);
            System.out.println(schedule.get());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完一定要记得关闭
            executorService1.shutdown();
            executorService2.shutdown();
            executorService3.shutdown();
            executorService4.shutdown();
        }
    }
}
