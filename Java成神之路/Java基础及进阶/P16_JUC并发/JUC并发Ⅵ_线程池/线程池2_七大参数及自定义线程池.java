package P16_JUC并发.JUC并发Ⅵ_线程池;


import java.util.concurrent.*;

public class 线程池2_七大参数及自定义线程池 {
    /**
       public ThreadPoolExecutor(
              核心线程池大小(已有的线程数)    int corePoolSize,
              最大核心线程池大小             int maximumPoolSize,
              超时了没有人调用就会释放        long keepAliveTime,
              超时单位                     TimeUnit unit,
              阻塞队列                     BlockingQueue<Runnable> workQueue,
              线程工厂。创建线程的，一般不动   ThreadFactory threadFactory,
              拒绝策略                     RejectedExecutionHandler handler
       )

       public static ExecutorService newSingleThreadExecutor() {
              return new ThreadPoolExecutor(1, 1,
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
                                           60L,TimeUnit.SECONDS,
                                          new SynchronousQueue<Runnable>());
       }
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 自定义线程池！工作中使用ThreadPoolExecutor
        ExecutorService executorService = new ThreadPoolExecutor(
                2,          // 核心线程池大小，定义了可以同时运行的最小任务数量。即这个线程池已经创建好2个线程，你随调随用。
                5,          /* 最大核心线程池大小。
                               当任务数大于核心线程数大小，并不会立马去创建线程供任务使用，而是把任务放到下面我们定义的阻塞队列中。
                               当阻塞队列也满了，还有多余的任务时，这个时候才会去创建线程。并且创建的线程数始终不能超过5个。 */
                3,          // 超时时间。当线程数大于核心线程数时，多余的空闲线程存活的最长时间，超过该时间则会销毁该线程。
                TimeUnit.SECONDS,             // 超时单位
                new LinkedBlockingDeque<>(3), // 阻塞队列
                Executors.defaultThreadFactory(),    // 线程工厂。创建线程的，一般不动
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略(有四种拒绝策略)。前提是已经达到最大线程数量了
                // new ThreadPoolExecutor.AbortPolicy()         // 如果线程队列已满，丢弃任务并抛出RejectedExecutionException异常。
                // new ThreadPoolExecutor.DiscardPolicy()       // 如果线程队列已满，则后续提交的任务都会被丢弃，不抛出异常。
                // new ThreadPoolExecutor.DiscardOldestPolicy() // 丢弃队列最前面的任务，然后重新提交被拒绝的任务。
                // new ThreadPoolExecutor.CallerRunsPolicy()    // 由调用线程处理该任务。比如主线程(main)调用线程池执行任务，
                                                                // 如果线程队列已满，那么就会由主线程直接运行该任务。
        );

       /* 引发java.util.concurrent.RejectedExecutionException的场景：
         1、当你的排队策略为有界队列，并且配置的拒绝策略是 ThreadPoolExecutor.AbortPolicy，
            当线程池的线程数量已经达到了maximumPoolSize的时候，你再向它提交任务，就会抛出ThreadPoolExecutor.AbortPolicy异常。
            从而引发java.util.concurrent.RejectedExecutionException
         2、线程池显式的调用了shutdown()之后，再向线程池提交任务的时候，如果你配置的拒绝策略是ThreadPoolExecutor.AbortPolicy的话，
            这个异常就被会抛出来。从而引发java.util.concurrent.RejectedExecutionException */

        /**
         * 一、线程基本规则
         *    1、默认情况下，线程池在初始的时候，线程数为0。当接收到一个任务时，如果线程池中存活的线程数小于corePoolSize核心线程，则新建一个线程。
         *    2、如果所有运行的核心线程都在忙，超出核心线程处理的任务，执行器更多地选择把任务放进队列，而不是新建一个线程。
         *    3、如果一个任务提交不了到队列，在不超出最大线程数量情况下，会新建线程。超出了就会报错。
         * 二、排队策略
         *    1、直接提交，用SynchronousQueue。特点是不保存，直接提交给线程，如果没线程，则新建一个。
         *    2、无限提交，用类似LinkedBlockingQueue无界队列。特点是保存所以核心线程处理不了的任务，队列无上限，最大线程也没用。
         *    3、有限提交，用类似ArrayBlockingQueue有界队列。特点是可以保存超过核心线程的任务，并且队列也是有上限的。
         *       超过上限，新建线程（满了抛错）。更好地保护资源，防止崩溃，也是最常用的排队策略。
         */
        try {
            for (int i = 1; i <= 10; i++) {
                // 线程池接受的最大任务数 = 阻塞队列数 + 最大线程池大小
                // 如果 任务数 > 阻塞队列数 + 最大线程池大小,继续往线程池中执行任务，会执行我们的拒绝策略
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "--OK"); // 运行可以发现同步线程数最大值是5
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // executorService.shutdown();
        }

        System.out.println("-----------------------------------------------------------------------");

        /*
          线程池提交任务的两种方式：execute与submit的区别
             execute：只能提交 Runnable 类型的任务，无返回值，如果遇到异常会直接抛出。
             submit：既可以提交 Runnable 类型的任务，也可以提交 Callable 类型的任务，如果遇到异常不会直接抛出。
                    提交 Callable 类型的任务时会有一个类型为Future的返回值，但当任务类型为 Runnable 时，返回值为null。
                    并且只有在使用Future的get方法获取返回值时，才会抛出异常。
         */
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("线程处理开始...");
                int a = 1;
                int b = 3;
                System.out.println("除以0的结果为：" + b/a);
                System.out.println("线程处理结束...");
                return "0";
            }
        };
        Future<String> future = executorService.submit(callable);
        System.out.println("任务执行完成，结果为：" + future.get());
        System.out.println("线程池中的所有任务是否已经执行完毕并且已经关闭：" + executorService.isTerminated());

        // 调用shutdown()后，线程池会逐渐停止，但不会立即停止。
        // 当线程池中的任务都执行完毕后，shutdown()会将所有的线程都关闭，这时线程池就终止了。
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("线程池中的所有任务是否已经执行完毕并且已经关闭：" + executorService.isTerminated());
    }
}
