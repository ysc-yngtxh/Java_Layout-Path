package L12_线程.线程Ⅲ_线程安全.线程9_死锁概念;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
  死锁代码要会写，一般面试官要求你会写，只有会写的，才会在以后的开发中注意这个事儿，因为死锁很难调试
 */
public class 死锁代码 {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();

        // t1和t2两个线程共享o1,o2
        Thread t1 = new MyThread5(o1, o2);
        Thread t2 = new MyThread6(o1, o2);

        t1.start();
        t2.start();
    }
}
class MyThread5 extends Thread{
    Object o1;
    Object o2;
    public MyThread5(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }
    public void run(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date()) + " MyThread5 开始执行");
        try {
            synchronized(o1) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date()) + " MyThread5 锁住 o1");
                Thread.sleep(1000); // 此处等待是给MyThread6能锁住机会
                synchronized(o2) {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date()) + " MyThread5 锁住 o2");
                    Thread.sleep(1000*60);
                    // 为测试，占用了就不放。所以o2资源导致了o1资源持续被占用无法释放，因为线程同步机制，MyThread6代码块儿就无法调用o1的资源
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class MyThread6 extends Thread{
    Object o1;
    Object o2;
    public void run(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date()) + " MyThread6 开始执行");
        try {
            synchronized(o2) {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date()) + " MyThread6 锁住 o2");
                Thread.sleep(1000); // 此处等待是给MyThread6能锁住机会
                synchronized(o1) {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date()) + " MyThread6 锁住 o1");
                    Thread.sleep(1000*60); // 为测试，占用了就不放
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public MyThread6(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }
}