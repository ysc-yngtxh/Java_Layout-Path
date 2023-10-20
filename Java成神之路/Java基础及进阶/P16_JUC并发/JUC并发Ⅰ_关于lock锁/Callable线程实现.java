package P16_JUC并发.JUC并发Ⅰ_关于lock锁;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Callable线程实现 {
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
