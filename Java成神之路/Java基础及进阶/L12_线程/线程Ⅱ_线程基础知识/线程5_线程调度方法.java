package L12_线程.线程Ⅱ_线程基础知识;

/*
  关于线程的调度
    1、常见的线程调度模型有哪些?
         抢占式调度模型：
             哪个线程的优先级比较高，抢到的CPU时间片的效率就高一些/多一些
         均分是调度模型：
             平均分配CPU时间片，每个线程占有的CPU时间片时间长度一样。平均分配，一切平等。
             有一些编程语言，线程调度模型采用的是这种方式
    2、Java中提供了哪些方法是和线程调度有关系的？
        1)、实例方法：setPriority(int newPriority)
                    void setPriority(int newPriority) 设置线程的优先级
                    int getPriority() 获取线程优先级
                    最低优先级1
                    默认优先级5
                    最高优先级10
                    优先级比较高的获取CPU时间片可能会多一些。（但也不完全是，大概率是高的）

         2)、静态方法：yield()
                     static void yield()  让位方法(指的是如果当前线程抢到时间片，就把时间片让掉，然后和其他的线程在同一起点重新去争抢)
                     yield() 方法不是阻塞方法。是使当前线程从执行状态（运行状态）变为可执行态（就绪状态）。
                     而CPU会从众多的可执行态里选择，也就是说，执行 yield() 的线程还是有可能会被再次执行到的，
                     并不是说一定会执行其他线程而执行yield()的线程在下一次中不会执行到了。
                     注意：在回到就绪之后，有可能还会再次抢到。但是如果当前线程持有锁并调用 yield()，它不会释放锁。
                          因此，即使当前线程让出 CPU，其他试图获取该锁的线程仍然会被阻塞，直到当前线程释放锁。

         3)、实例方法：join()
                     void join()  插队
                     join()是 Thread 类中的一个方法，当我们需要让线程按照自己指定的顺序执行的时候，就可以利用这个方法。
                     class MyThread1 extends Thread {
                        public void doSome(){
                           MyThread2 t = new MyThread2()
                           t.join();  // t线程先执行，主线程阻塞。直到t线程结束，主线程才可以继续执行。
                        }
                     }
                     class MyThread2 extends Thread{}
                     当线程 A 调用线程 B 的 join() 方法时，线程 A 会进入阻塞状态，直到线程 B 执行完毕。所以是需要释放锁操作的。
 */
class YSC implements Runnable{
    @Override
    public void run() {
        // 默认的优先级是5
        for (int i = 1; i <= 100; i++) {
            // 每10个让位一次
            if(i % 10 == 0) {
                Thread.yield();   // 当前线程时间片让掉，和其它线程重新争抢时间片
            }
            System.out.println(Thread.currentThread().getName() + " --> " + i);
        }
    }
}
public class 线程5_线程调度方法 {
    public static void main(String[] args) {
        Thread t = new Thread(new YSC());
        t.setName("t");
        t.start();
        t.setPriority(10);  // 优先级较高的，只是抢到的CPU时间片相对多一些。大概率方向更偏向优先级比较高的

        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        }

        try {
            // 主线程能执行到这儿，说明主线程的for循环已经执行完毕了
            // 主程序执行t线程的join()方法，表示这条语句后主线程的代码不执行，直到t线程结束，主线程才可以继续。
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over!");  // 所以这条语句是最后执行的
    }
}