package P16_JUC并发.JUC并发Ⅵ_线程池;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class 线程池2_七大参数及自定义线程池 {
    /**
       public ThreadPoolExecutor(
              核心线程池大小                              int corePoolSize,
              最大核心线程池大小                           int maximumPoolSize,
              空闲线程存活时间                             long keepAliveTime,
              超时时间单位                                TimeUnit unit,
              工作队列（阻塞队列）                         BlockingQueue<Runnable> workQueue,
              线程工厂（创建新线程的，一般不动）             ThreadFactory threadFactory,
              拒绝策略（线程数量超过最大线程数时，拒绝任务）   RejectedExecutionHandler handler
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
        // 自定义线程池！工作中推荐使用ThreadPoolExecutor
        // TODO 注意：上述中方法返回类型 ExecutorService 是一个接口,而 ThreadPoolExecutor 是 ExecutorService 的一个具体实现类。
        //           ThreadPoolExecutor提供了对线程池的细粒度控制，例如核心线程数、最大线程数、空闲线程存活时间、工作队列等。
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                5,    // 核心线程池大小。
                10,         /* 最大核心线程池大小。
                               当任务数大于核心线程数大小，并不会立马去创建线程供任务使用，而是把任务放到下面我们定义的阻塞队列中。
                               当阻塞队列也满了，还有多余的任务时，这个时候才会去创建新线程。并且创建的线程数始终不能超过5个。
                               如果创建的新线程处于空闲状态，且超过设置的存活时间，那么会被进行销毁处理。*/
                3,          /* 空闲线程存活时间（超时时间）
                               当线程数大于核心线程数时，多余的空闲线程存活的最长时间，超过该时间则会销毁该线程。*/
                TimeUnit.SECONDS,    // 超时时间单位（如：TimeUnit.SECONDS[秒]、TimeUnit.MILLISECONDS[毫秒]...）
                new LinkedBlockingDeque<>(3), // 工作队列（任务等待队列）。任务队列用于存放等待执行的任务，当线程池中的线程都在忙碌时，新任务会进入阻塞队列等待。
                Executors.defaultThreadFactory(),     /* 线程工厂（用于创建新线程）
                                                         默认的线程工厂采用new Thread()方式创建，且创建的线程名具有统一风格：pool-m-thread-n(m为线程池编号,n为线程池中的线程编号)
                                                         除了使用默认工厂外，也可以自定义工厂，设置线程的属性，如线程名称、优先级、守护线程属性等。*/
                new ThreadPoolExecutor.AbortPolicy()  // 拒绝策略(有四种拒绝策略)。前提是已经达到最大线程数量了
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

        /** 总结：
         *  一、线程基本规则
         *     1、默认情况下，线程池在创建时并不会预先创建核心线程。核心线程的创建是按需的，即在有任务提交时才会创建。
         *        ①、默认行为：线程池初始时没有线程
         *                    当你创建一个ThreadPoolExecutor时，线程池中的线程数为0。
         *                    核心线程（corePoolSize）并不是在线程池创建时立即创建的，而是在任务提交时按需创建。
         *        ②、任务提交时的行为
         *            当有任务提交时：
         *              如果当前线程数小于核心线程（corePoolSize），线程池会立即创建一个新线程来处理任务。
         *              如果线程数已达到核心线程（corePoolSize），新任务会被放入工作队列（workQueue）等待执行。
         *              如果队列已满且线程数小于最大线程（maximumPoolSize），线程池会创建新线程来处理任务。
         *              如果线程数已达到最大线程（maximumPoolSize）且队列已满，新任务会被拒绝，触发拒绝策略。
         *         ③、预先创建核心线程的方法
         *             如果你希望线程池在创建时就预先创建核心线程，可以通过调用 ThreadPoolExecutor 的
         *             prestartAllCoreThreads() 方法来实现。这个方法会立即启动所有核心线程，即使没有任务提交。
         *     2、工作队列中只有等待执行的任务，没有提供线程资源。只有等其他的线程资源释放出来，队列中的任务才能被执行。
         *     3、空闲线程：指的是当前没有执行任务且处于等待状态的线程
         *         ①、核心线程（core threads）：当核心线程中存在暂时没有任务执行的线程，那么这部分线程也可以被称为空闲线程。
         *                (1)、默认情况下，核心线程即使处于空闲状态也不会被销毁。
         *                (2)、但是如果设置了 allowCoreThreadTimeOut(true)，核心线程在空闲时间超过keepAliveTime后也会被销毁。
         *         ②、非核心线程（non-core threads）：
         *                当创建了最大线程数，那么超过核心线程数的那部分线程完成了之前的任务，且暂时没有新任务时也可以称为空闲线程。
         *                但这部分如果超过 空闲线程的存活时间（keepAliveTime），那么这部分线程会被销毁。
         *
         *  二、排队策略
         *    1、直接提交，用SynchronousQueue。特点是不保存，直接提交给线程，如果没线程，则新建一个。
         *    2、无限提交，用类似LinkedBlockingQueue无界队列。特点是保存所以核心线程处理不了的任务，队列无上限，最大线程也没用。
         *    3、有限提交，用类似ArrayBlockingQueue有界队列。特点是可以保存超过核心线程的任务，并且队列也是有上限的。
         *       超过上限，新建线程（满了抛错）。更好地保护资源，防止崩溃，也是最常用的排队策略。
         */

        try {
            // 预先创建核心线程。即不论线程中是否有任务，都会创建核心线程
            executorService.prestartAllCoreThreads();
            // 设置核心线程在空闲时间超过 空闲线程的存活时间（keepAliveTime）后也会被销毁
            executorService.allowCoreThreadTimeOut(true);

            for (int i = 1; i <= 20; i++) {
                // 线程池接受的最大任务数 = 阻塞队列数 + 最大线程池大小
                // 如果 任务数 > 阻塞队列数 + 最大线程池大小,继续往线程池中执行任务，会执行我们的拒绝策略
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "--OK"); // 运行可以发现同步线程数最大值是10
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
             submit： 既可以提交 Runnable 类型的任务，也可以提交 Callable 类型的任务，如果遇到异常不会直接抛出。
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
