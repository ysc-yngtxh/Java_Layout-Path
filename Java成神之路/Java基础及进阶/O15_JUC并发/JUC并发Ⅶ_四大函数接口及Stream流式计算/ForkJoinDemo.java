package O15_JUC并发.JUC并发Ⅶ_四大函数接口及Stream流式计算;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinDemo extends RecursiveTask<Long> {
    private static final long serialVersionUID = 2947412188376658390L;
    /**
     * ForkJoin在Jdk1.7，并行执行任务！提高效率，大数据量
     * 大数据：Map Reduce(把大任务拆分为小任务)
     * </p>
     * 求和计算的任务！
     * 3000   6000(ForkJoin)  9000(Stream并行流)
     * 如何使用ForkJoin
     *    1、forkJoinPool  通过它来执行
     *    2、计算任务forkJoinPoll,execute(ForkJoinTask task)
     *    3、计算类要继承ForkJoinTask
     */
    private Long start;
    private Long end;
    private Long temp = 10000L; // 临界值

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    //计算方法
    @Override
    protected Long compute() {
        if((end-start) < temp) {
            long sum = 0L;
            for(Long i = start; i <= end; i++){
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end)/2L; // 中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork(); // 拆分任务，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle+1, end);
            task2.fork(); // 拆分任务，把任务压入线程队列
            return task1.join() + task2.join();
        }
    }
}

class Test{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
        test2();
        test3();
    }
    // 普通程序员
    public static void test1(){
        long sum = 0L;
        Long start = System.currentTimeMillis();
        for (long i = 0L; i < 10_0000_0000L; i++) {
            sum += i;
        }
        Long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + " => for循环 执行所需时间：" + (end-start) + "毫秒");
    }
    // 高级程序员：会使用ForkJoin
    public static void test2() throws ExecutionException, InterruptedException {
        Long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();
        Long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + " => ForkJoin 执行所需时间：" + (end-start) + "毫秒");
    }
    // 超级程序员：使用Stream并行流
    public static void test3(){
        Long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        Long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + " => Stream并行流 执行所需时间：" + (end-start) + "毫秒");
    }
}
