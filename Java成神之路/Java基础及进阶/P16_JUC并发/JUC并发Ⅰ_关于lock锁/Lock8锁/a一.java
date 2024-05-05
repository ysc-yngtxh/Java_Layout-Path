package P16_JUC并发.JUC并发Ⅰ_关于lock锁.Lock8锁;

import java.util.concurrent.TimeUnit;

/**
 * Lock8锁：其实就是关于锁的8个问题
 * 情况一：类中两个方法都加上了synchronized关键字，只创建一个对象进行方法调用，意味着共用一把锁。
 *       并且这个时候A线程在执行时会睡眠2秒。这个时候执行main方法，先打印出来的是 "发短信" 还是 "打电话" ？
 */
public class a一 {
    public static void main(String[] args) {
        Test1 test = new Test1();
        new Thread(test::Send, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);  // 睡眠1秒，不再使用Thread.sleep()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(test::Call, "B").start();
    }
}
class Test1{
    public synchronized void Send(){
        try {
            TimeUnit.SECONDS.sleep(2);  // 睡眠，不再使用Thread.sleep()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void Call(){
        System.out.println("打电话");
    }
}

// 回答：先打印的是”发短信“。因为两个线程共用一把对象锁的情况下，线程时需要排队的，在已知A线程先启动，B线程只有等待。
