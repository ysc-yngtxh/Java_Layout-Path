package P16_JUC并发.JUC并发Ⅰ_关于lock锁.Lock8锁;

import java.util.concurrent.TimeUnit;

/**
 * 情况六：类中方法都加上static synchronized关键字
 *       这个时候我在main方法中创建两对象，并分别去执行A和B线程。。。 先打印出来的是 "发短信" 还是 "打电话" ？
 */
public class f六 {
    public static void main(String[] args) {
        Test6 test1 = new Test6();
        Test6 test2 = new Test6();
        // 使用Test6静态方法 Send()
        new Thread(() -> test1.Send(), "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);  // 睡眠，不再使用Thread.sleep()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 使用Test6对象正常方法 Call()
        new Thread(() -> test2.Call(), "B").start();
    }
}
class Test6{
    public static synchronized void Send() {
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
 * 回答：先打印的"发短信"，还是那句话，无论怎么操作，加了static关键字的那么，锁只有一把（类锁），线程需要排队执行。
 */
