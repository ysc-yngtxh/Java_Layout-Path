package P16_JUC并发.JUC并发Ⅰ_关于lock锁;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock锁 {
    /**
     * Synchronized与Lock的区别：
     *    1、Synchronized是内置的Java关键字，Lock是一个Java接口类
     *    2、Synchronized无法判断获取锁的状态，Lock可以判断是否获取到了锁
     *    3、Synchronized会自动释放锁，Lock必须手动释放锁(如果不释放锁----死锁)
     *    4、Synchronized：线程1(获得锁，阻塞)，线程2(等待)
     *       Lock锁不一定会等待下去
     *    5、Synchronized可重入锁，不可以中断，非公平(不公平，不遵循先来后到)
     *       Lock可重入锁，可中断，可判断锁状态(tryLock)，默认是非公平(非常不公平，可插队)但是可以实现为公平锁
     *    6、Synchronized适合少量代码同步问题
     *       Lock适合锁大量的同步代码
     *
     * 可重入锁：允许同一线程多次获得锁。就是说某个线程已经获得某个锁，在后续代码中可以再次获取锁而不会出现死锁。
     * 公 平 锁：指多个线程同时尝试获取锁时，锁的获取按照线程的请求顺序进行分配。
     *          例如：多线程情况下释放锁的瞬间，其他正在等待锁释放的线程会去抢占锁资源，等待时间长的线程就会抢到锁。
     * 非公平锁：则是指多个线程同时尝试获取锁时，无论线程请求锁的先后顺序如何，锁的获取总是由系统随机分配的。
     *          非公平锁性能比公平锁高5-10倍，因为公平锁需要在多核的情况下维护一个队列
     *
     * 在资源竞争不是很激烈的情况下，Synchronized的性能要优于ReentrantLock，
     * 但是在资源竞争很激烈的情况下，Synchronized的性能会下降几十倍，但是ReentrantLock的性能能维持常态；
     * 严格来说在单线程Synchronized比Lock锁要效率高的，但是Lock的API方便呀！😁😁😁
     *
     * 有关 AQS 的一些原理跟机制：https://cloud.tencent.com/developer/article/2378820
     */
    public static void main(String[] args) {
        Dataes data = new Dataes();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    data.demo0();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "YSC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo4();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

class Dataes {
    private int num = 1;
    final Lock lock = new ReentrantLock();   // 创建lock锁，相当于Synchronized关键字，默认不公平锁
    // Lock lock1 = new ReentrantLock(true); // 创建lock锁，赋值为true表示使用公平锁
    Condition condition0 = lock.newCondition(); // 获得lock锁的监听器
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void demo0() throws InterruptedException {
        try {
            while (num != 1) {
                // 线程等待
                condition0.await();
            }
            if (lock.tryLock()) { // 尝试获取锁，如果锁不可用，不会导致当前线程被禁用，当前线程仍然会继续往下执行代码
                System.out.println(Thread.currentThread().getName() + "---demo0正在尝试获取锁...");
                lock.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 解锁
        }
    }

    public void demo1() throws InterruptedException {
        // 启动线程
        lock.lock();
        try {
            while (num != 1) {
                // 线程等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "---demo1");
            num = 2;
            // condition1.signalAll(); // 唤醒所有线程
            condition2.signal(); // 唤醒指定线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 解锁
        }
    }

    public void demo2() throws InterruptedException {
        // 启动线程
        lock.lock();
        try {
            while (num != 2) {
                // 线程等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "---demo2");
            num = 3;
            // condition2.signalAll(); // 唤醒所有线程
            condition3.signal(); // 唤醒指定线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void demo3() throws InterruptedException {
        // 启动线程
        lock.lock();
        try {
            while (num != 3) {
                // 线程等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "---demo3");
            num = 1;
            // condition3.signalAll(); // 唤醒所有线程
            condition1.signal(); // 唤醒指定线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void demo4() throws InterruptedException {
        // 启动线程
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "---demo4");
            // condition3.signalAll(); // 唤醒所有线程
            condition1.signal(); // 唤醒指定线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
