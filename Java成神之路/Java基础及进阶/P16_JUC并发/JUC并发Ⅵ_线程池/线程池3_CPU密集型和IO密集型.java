package P16_JUC并发.JUC并发Ⅵ_线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class 线程池3_CPU密集型和IO密集型 {
    /**
     * CPU密集型，IO密集型(调优)
     * 线程池的最大大小(线程)到底该如何设置
     *    1、CPU密集型，几核，就是几，可以保持CPU的效率最高。其线程大部分时间处于运行状态
     *    2、IO密集型 > 判断你程序中十分耗IO的线程。大部分时间处于等待/阻塞状态
     *
     * 1. CPU 密集型任务【线程数 ≈ CPU 核心数】
     *    特点：任务主要消耗 CPU 资源，例如计算、数据处理等。
     *    设置建议：核心线程数通常设置为 CPU核数+1。
     *            例如，如果你的机器有 8个 CPU核心，可以设置核心线程数为 9。
     *    原因：过多的线程会导致频繁的上下文切换，反而降低性能。
     *         额外的一个线程可以在某个线程因其他原因（如页缺失）暂停时，充分利用 CPU。
     *
     * 2. IO 密集型任务【线程数 ≈ CPU核心数*(1+平均等待时间/平均计算时间)】
     *    特点：任务主要消耗 IO 资源，例如文件读写、网络请求、数据库操作等。
     *    设置建议：核心线程数可以设置为 CPU核心数*(1+平均等待时间/平均计算时间) 或更高。
     *            例如：如果你的机器有 8个 CPU核心，根据估算公式：线程数 = CPU核心数 * 目标CPU利用率 * (1 + 等待时间/计算时间)
     *                 假设50% CPU利用率，等待时间是计算时间的2倍。可以设置核心线程数为 12 或更多。
     *    原因：IO 操作通常会阻塞线程，导致 CPU 空闲，因此可以创建更多的线程来充分利用 CPU。
     *         具体线程数可以根据任务的阻塞时间和 CPU 利用率调整。
     *
     * 3、注意事项
     *    线程数不是越多越好：过多的线程会导致上下文切换开销增加，反而降低性能。
     *    监控和调优：在实际运行中，可以通过监控 CPU 利用率、任务排队情况等指标，动态调整线程数。
     *    任务队列的选择：根据任务类型选择合适的任务队列（如无界队列、有界队列或同步队列）。
     */
    public static void main(String[] args) {
        // 获取当前用户CPU的核数
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println( "当前系统中存在的CPU核数：" + availableProcessors );

	    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				Runtime.getRuntime().availableProcessors() + 1,  // 根据用户的CPU核数来设置核心线程池大小
				Runtime.getRuntime().availableProcessors() * 2,  // 根据用户的CPU核数来设置最大核心线程池大小
				3,                                    // 超时时间,超过核心线程的线程在此时间后会被释放
				TimeUnit.SECONDS,                     // 超时单位
				new LinkedBlockingDeque<>(3), // 阻塞队列，容量为3
				Executors.defaultThreadFactory(),     // 线程工厂（创建线程的，一般不动）
				new ThreadPoolExecutor.AbortPolicy()  // 拒绝策略
		);

		try {
			for (int i = 1; i <= availableProcessors * 10 / 2; i++) { // 最大任务数 = 阻塞队列数 + 最大线程池大小
				threadPoolExecutor.execute(() -> {
					// 运行可以发现同步线程数最大值是19
					System.out.println(Thread.currentThread().getName() + " -- OK");
				});
			}
			// 定期监控线程池状态
			threadPoolExecutor.getPoolSize();      // 当前线程数
			threadPoolExecutor.getActiveCount();   // 活动线程数
			threadPoolExecutor.getQueue().size();  // 队列中任务数
			threadPoolExecutor.getCompletedTaskCount(); // 已完成任务数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPoolExecutor.shutdown();
		}
	}

}
