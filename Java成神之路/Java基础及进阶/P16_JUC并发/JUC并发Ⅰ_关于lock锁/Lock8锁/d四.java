package P16_JUC并发.JUC并发Ⅰ_关于lock锁.Lock8锁;

import java.util.concurrent.TimeUnit;

/**
 * 情况四：一个类中一个有synchronized关键字，一个没有。这个时候在main方法中创建两对象，并分别执行A和B线程，
 *        先打印出来的是 "发短信" 还是 "打电话" ？
 */
public class d四 {
    public static void main(String[] args) {
        Test4 test1 = new Test4();
        Test4 test2 = new Test4();
        new Thread(test1::Send, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);  // 睡眠，不再使用Thread.sleep()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(test2::Call, "B").start();
    }
}
class Test4{
    public synchronized void Send() {
        try {
            TimeUnit.SECONDS.sleep(2);  // 睡眠，不再使用Thread.sleep()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public void Call(){
        System.out.println("打电话");
    }
}

/**
 * 回答：先打印的是"打电话"。B线程中都没有锁，所以不需要进行排队等待A线程结束
 */
