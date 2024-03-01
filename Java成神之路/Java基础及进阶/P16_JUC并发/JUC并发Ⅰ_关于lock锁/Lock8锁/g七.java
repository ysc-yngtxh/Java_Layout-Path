package P16_JUC并发.JUC并发Ⅰ_关于lock锁.Lock8锁;

import java.util.concurrent.TimeUnit;

/**
 * 情况七：类中两方法，一方法被static synchronized修饰，一个方法被synchronized修饰。
 *        这个时候我在main方法中创建一个对象，执行A和B线程。。。先打印出来的是 "发短信" 还是 "打电话" ？
 */
public class g七 {
    public static void main(String[] args) {
        Test7 test7 = new Test7();
        // 使用Test7的静态方法 Send()
        new Thread(() -> test7.Send(), "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);  // 睡眠，不再使用Thread.sleep()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 使用Test7对象正常方法 Call()
        new Thread(() -> test7.Call(), "B").start();
    }
}
class Test7{
    public static synchronized void Send(){
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

/**
 * 回答：先打印的是"打电话"。一个是静态同步方法，锁是类锁。一个是普通同步方法，锁是普通锁。
 *      不是同一把锁的情况下，先执行的是"打电话"
 */