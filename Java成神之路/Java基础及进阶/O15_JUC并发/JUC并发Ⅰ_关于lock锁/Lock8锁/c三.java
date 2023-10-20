package O15_JUC并发.JUC并发Ⅰ_关于lock锁.Lock8锁;

import java.util.concurrent.TimeUnit;

/**
 * 情况二：一个类中一个有synchronized关键字，一个没有。这个时候执行main方法，先打印出来的是 "发短信" 还是 "打电话" ？
 */
public class c三 {

    public static void main(String[] args) {

        Test3 test3 = new Test3();

        new Thread(test3::Send, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);  // 睡眠，不再使用Thread.sleep()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(test3::Call, "B").start();
    }
}

class Test3{
    public synchronized void Send(){
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
 * 回答：因为同在一个类中，一个方法加上了Synchronized关键字，就相当于这个类对象线程同步
 *      所以，先打印的是"发短信"
 */
