package O15_JUC进阶理解.JUCⅥ_线程池;


import java.util.concurrent.*;

public class B七大参数及自定义线程池 {
    /**

       public ThreadPoolExecutor(
       初始核心线程池大小(已有的线程数) int corePoolSize,
       最大核心线程池大小             int maximumPoolSize,
       超时了没有人调用就会释放        long keepAliveTime,
       超时单位                     TimeUnit unit,
       阻塞队列                     BlockingQueue<Runnable> workQueue,
       线程工厂。创建线程的，一般不动   ThreadFactory threadFactory,
       拒绝策略                     RejectedExecutionHandler handler)

       public static ExecutorService newSingleThreadExecutor() {
               return new FinalizableDelegatedExecutorService
                   (new ThreadPoolExecutor(1, 1,
                                           0L, TimeUnit.MILLISECONDS,
                                           new LinkedBlockingQueue<Runnable>()));
       }

       public static ExecutorService newFixedThreadPool(int nThreads) {
              return new ThreadPoolExecutor(nThreads, nThreads,
                                            0L, TimeUnit.MILLISECONDS,
                                            new LinkedBlockingQueue<Runnable>());
       }

       public static ExecutorService newCachedThreadPool() {
             return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                          * 60L,TimeUnit.SECONDS,
                                          new SynchronousQueue<Runnable>());
       }
     */

    public static void main(String[] args) {
        // 自定义线程池！工作中使用ThreadPoolExecutor
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
                2,          // 核心线程池大小
                5,          // 最大核心线程池大小
                3,          // 超时时间。当超过核心线程数的线程，线程池会让该线程保持存活keepAliveTime时间，超过该时间则会销毁该线程。
                TimeUnit.SECONDS,             // 超时单位
                new LinkedBlockingDeque<>(3), // 阻塞队列
                Executors.defaultThreadFactory(),    // 线程工厂。创建线程的，一般不动
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略(有四种拒绝策略)
                //new ThreadPoolExecutor.CallerRunsPolicy()    // 哪里来的就哪里去
                //new ThreadPoolExecutor.DiscardPolicy()       // 队列满了，就丢掉任务，不会抛异常
                //new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试和最早的队列进行竞争，也不会抛出异常

                /*这里要注意一下：
                     1、当你的排队策略为有界队列，并且配置的拒绝策略是ThreadPoolExecutor.AbortPolicy，
                        当线程池的线程数量已经达到了maximumPoolSize的时候，你再向它提交任务，就会抛出ThreadPoolExecutor.AbortPolicy异常。
                        从而引发java.util.concurrent.RejectedExecutionException
                     2、线程池显示的调用了shutdown()之后，再向线程池提交任务的时候，如果你配置的拒绝策略是ThreadPoolExecutor.AbortPolicy的话，
                        这个异常就被会抛出来。从而引发java.util.concurrent.RejectedExecutionException
                */
        );

        /**
         * 一、线程基本规则
         *    1、默认情况下，线程池在初始的时候，线程数为0。当接收到一个任务时，如果线程池中存活的线程数小于corePoolSize核心线程，则新建一个线程。
         *    2、如果所有运行的核心线程都都在忙，超出核心线程处理的任务，执行器更多地选择把任务放进队列，而不是新建一个线程。
         *    3、如果一个任务提交不了到队列，在不超出最大线程数量情况下，会新建线程。超出了就会报错。
         * 二、排队策略
         *    1、直接提交，用SynchronousQueue。特点是不保存，直接提交给线程，如果没没线程，则新建一个。
         *    2、无限提交，用类似LinkedBlockingQueue无界队列。特点是保存所以核心线程处理不了的任务，队列无上限，最大线程也没用。
         *    3、有限提交，用类似ArrayBlockingQueue有界队列。特点是可以保存超过核心线程的任务，并且队列也是有上限的。超过上限，新建线程（满了抛错）。更好地保护资源，防止崩溃，也是最常用的排队策略。
         */
        try {
            for (int i = 1; i <= 9; i++) { // 最大线程数 = 阻塞队列数 + 最大线程池大小
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"--OK"); // 运行可以发现同步线程数最大值是5
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
      /*


       */
}
