package P16_JUC并发.JUC并发Ⅰ_关于lock锁.Lock8锁;

import java.util.concurrent.TimeUnit;

/**
 * 情况五：类中方法都加上static synchronized关键字，这个时候我在main方法中创建一个对象，先打印出来的是 "发短信" 还是 "打电话" ？
 */
public class e五 {
    public static void main(String[] args) {
        Test5 test5 = new Test5();
        // 使用Test5的静态方法 Send()
        new Thread(() -> Test5.Send(), "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);  // 睡眠，不再使用Thread.sleep()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 使用Test5的静态方法 Call()
        new Thread(() -> test5.Call(), "B").start();
    }
}
class Test5{
    public static synchronized void Send(){
        try {
            TimeUnit.SECONDS.sleep(2);  // 睡眠，不再使用Thread.sleep()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public static synchronized void Call(){
        System.out.println("打电话");
    }
}

/**
 * 回答：先执行的是"发短信"。因为不论是A线程还是B线程执行的方法都加上了static关键字，那么这个时候也还是一把锁，但是这把锁是类锁。
 *      我们知道，类锁只有一把。线程在加锁情况下又是需要排队的，所以先执行的是"发短信"
 */