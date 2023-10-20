package P16_JUC并发.JUC并发Ⅴ_阻塞队列;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class 阻塞队列5_同步队列 {
    /**
     * 同步队列
     * 和其他的BlockingQueue不一样，SynchronousQueue不存储元素(就是说，这个队列中最多只能有一个元素)
     * 往该队列里 put 了一个元素，必须从里面先 take 取出来，否则就不能 put 进去值！
     */
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "->" + "put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + "->" + "put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + "->" + "put 3");
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();


        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "->take " + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "->take " + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "->take " + queue.take());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}
