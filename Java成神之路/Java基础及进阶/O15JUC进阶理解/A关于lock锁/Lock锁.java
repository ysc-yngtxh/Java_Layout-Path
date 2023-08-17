package O15JUC进阶理解.A关于lock锁;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock锁 {
    /**
     * Synchronized与Lock的区别：
     *     1、Synchronized是内置的Java关键字，Lock是一个Java类
     *     2、Synchronized无法判断获取锁的状态，Lock可以判断是否获取到了锁
     *     3、Synchronized会自动释放锁,Lock必须手动释放锁(如果不释放锁----死锁)
     *     4、Synchronized：线程1(获得锁，阻塞)，线程2(等待)
     *        Lock锁不一定会等待下去
     *     5、Synchronized可重入锁，不可以中断，公平(十分公平，先来后到)
     *        Lock可重入锁，可判断锁，非公平(非常不公平，可插队)
     *     6、Synchronized适合少量代码同步问题
     *        Lock适合锁大量的同步代码
     *
     * 严格来说Synchronized比Lock锁要效率高的，但是Lock的API方便呀！😁😁😁
     *
     */
    public static void main(String[] args) {
        Dataes data = new Dataes();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

    }
}

class Dataes{

    private int num = 1;
    Lock lock = new ReentrantLock(); //创建lock锁，相当于Synchronized关键字
    Condition condition1 = lock.newCondition(); //获得lock锁的监听器
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void demo1() throws InterruptedException {
        lock.lock();//启动线程。
        try {
            while (num!=1) {
                condition1.await();//线程等待
            }
            System.out.println(Thread.currentThread().getName() + "---AAAAA");
            num=2;
            //condition1.signalAll();唤醒所有线程
            condition2.signal();//唤醒指定线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }
    }

    public void demo2() throws InterruptedException {
            lock.lock();//启动线程。
            try {
                while (num!=2) {
                    condition2.await();//线程等待
                }
                System.out.println(Thread.currentThread().getName() + "---BBBBB");
                num=3;
                //condition2.signalAll();唤醒所有线程
                condition3.signal();//唤醒指定线程
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
    }

    public void demo3() throws InterruptedException {
           lock.lock();//启动线程。
           try {
               while (num!=3) {
                   condition3.await();//线程等待
               }
               System.out.println(Thread.currentThread().getName() + "---CCCCC");
               num=1;
               //condition3.signalAll();唤醒所有线程
               condition1.signal();//唤醒指定线程
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              lock.unlock();
          }
    }
}
