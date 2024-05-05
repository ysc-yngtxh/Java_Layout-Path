package L12_线程.线程Ⅳ_线程进阶内容;

import java.util.ArrayList;
import java.util.List;

/*
  一、关于Object类中的wait和notify方法。（生产者和消费者模式！）
      第一：wait和notify方法不是线程对象的方法，是Java中任何一个Java对象都有的方法，因为这两个方式是Object类中自带的。
            wait方法和notify方法不是通过线程对象调用的。不是这样的：t.wait(),t.notify();...

      第二：wait()方法作用？
            Object obj = new Object();
            obj.wait();
            表示：
                obj.wait()方法的调用，会让 "当前线程" (正在obj对象上活动的线程)进入等待状态，无限期等待，直到被唤醒为止

      第三：notify()方法作用？
            Object obj = new Object();
            obj.notify();
            表示：
                唤醒正在obj对象上等待的线程

            还有一个notifyAll()方法：
                 唤醒对象上处于等待的所有线程。

      ⚠️：当我们在synchronized块中使用wait()方法时，它会释放当前线程持有的对象锁，并使得当前线程进入该对象的等待锁定池。
          直到其他线程调用了该对象的notify()或notifyAll()方法，该线程才会进入对象锁定池准备获得对象锁并进入运行状态。

  二、使用wait方法和notify方法实现“生产者和消费者模式”
      什么是生产者和消费者模式？
          生产线程负责生产，消费线程负责消费，生产线程和消费线程要达到均衡。
          这是一种特殊的业务需求，在这种特殊的情况下需要使用wait方法和notify方法

      模拟这样一个需求：
          仓库我们采用List集合。
          List集合中假设只能存储1个元素
          1个元素就表示仓库满了
          如果List集合中元素个数是0,就表示仓库空了
          保证List集合中永远都是最多存储1个元素
 */
public class 线程10_Object类中的wait和notify方法 {
    public static void main(String[] args) {
        // 创建List仓库集合对象，共享的
        List<Object> list = new ArrayList<>();

        // 创建生产者线程对象
        Thread t1 = new Thread(new Producer(list), "生产者线程");
        // 创建消费者线程对象
        Thread t2 = new Thread(new Consumer(list), "消费者线程");

        // t1.setName("生产者线程");
        // t2.setName("消费者线程");
        t1.start();
        t2.start();
    }
}
// 生产线程
class Producer implements Runnable{
    private List<Object> list;
    public Producer(List<Object> list) {
        this.list = list;
    }
    @Override
    public void run() {
        // 一直生产(使用死循环来模拟一直生产)
        while(true) {
            // 给仓库对象list加一把锁
            synchronized(list) {
                if(list.size() > 0) {  // 大于0，说明仓库中已经有1个元素了。
                    try {
                        // 当前线程进入等待状态，并且释放list集合的锁
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 程序能够执行到此处，说明仓库是空的，可以生产
                Object obj = new Object();
                list.add(obj);
                System.out.println(Thread.currentThread().getName() + "-->" + obj);
                // 唤醒消费者消费
                list.notifyAll();
            }
        }
    }
}
// 消费线程
class Consumer implements Runnable{
    private List<Object> list;
    public Consumer(List<Object> list) {
        this.list = list;
    }
    @Override
    public void run() {
        while(true) {
            synchronized(list) {
                if(list.size() == 0) {
                    // 仓库空了，消费者线程等待，释放掉list集合的锁
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 程序能执行到此处，说明仓库中有数据，进行消费
                Object obj = list.remove(0);   // 删除指定下标的元素
                System.out.println(Thread.currentThread().getName() + "-->" + obj);
                // 唤醒生产者生产
                list.notifyAll();
            }
        }
    }
}