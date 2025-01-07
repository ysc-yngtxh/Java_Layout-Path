package P16_JUC并发.JUC并发Ⅰ_关于lock锁;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Callable线程实现 {
    /**
     * 三种方式的优缺点
     *   1、采用继承Thread类方式：
     *     （1）优点：编写简单，如果需要访问当前线程，无需使用Thread.currentThread()方法，直接使用this，即可获得当前线程。
     *     （2）缺点：因为线程类已经继承了Thread类，所以不能再继承其他的父类
     *   2、采用实现Runnable接口方式：
     *     （1）优点：线程类只是实现了Runnable接口，还可以继承其他的类。
     *     （2）缺点：编程稍微复杂，如果需要访问当前线程，必须使用Thread.currentThread()方法
     *   3、Runnable和Callable的区别：
     *     （1）Callable规定的方法是call()，Runnable规定的方法是run()
     *     （2）Callable的任务执行后可返回值，而Runnable的任务是不能返回值的
     *     （3）call方法可以抛出异常，run方法不可以，因为run方法本身没有抛出异常，所以自定义的线程类在重写run的时候也无法抛出异常
     *     （4）运行Callable任务可以拿到一个Future对象，表示异步计算的结果。
     *         它提供了检查计算是否完成的方法，以等待计算的完成，并检索计算的结果。
     *         通过Future对象可以了解任务的执行情况，可取消任务的执行，还可获取执行结果
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Demo demo = new Demo();
        /**
         * 这里要说一下为什么要使用FutureTask这个对象呢？
         *     因为我们去查看源码可以发现Runnable关联的有很多的对象，但是只有FutureTask这个对象与Callable这个接口有关系
         *     所以我们要通过这个FutureTask来实现Callable接口，获取返回值和抛出的异常。
         *     (可以理解FutureTask为Callable的适配类，Callable通过FutureTask去关联Runnable接口，来实现线程的操作)
         */
        FutureTask<Integer> task = new FutureTask<>(demo);

        new Thread(task, "A").start();
        new Thread(task, "B").start(); // 这里创建两个Demo对象并不会让结果出现两次。可以理解为结果缓存

        // 通过task对象的get方法获取返回结果.
        // 但是这一步会产生阻塞，因为它会去等待结果的返回。如果Demo类中的Call()方法被睡眠了或者其他异常，等待时间过长就会阻塞
        Integer o = task.get();
        System.out.println(o);
    }
}

class Demo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 322;
    }
}
