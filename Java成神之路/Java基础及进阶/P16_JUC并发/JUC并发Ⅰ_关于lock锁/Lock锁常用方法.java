package P16_JUC并发.JUC并发Ⅰ_关于lock锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-01 13:25
 * @apiNote TODO
 */
public class Lock锁常用方法 {
    /**
     * Lock 接口的主要方法
     *     1. void lock(): 执行此方法时, 如果锁处于空闲状态, 当前线程将获取到锁.
     *                     相反, 如果锁已经被其他线程持有, 将禁用当前线程, 直到当前线程获取到锁.
     *     2. void unlock()：执行此方法时, 当前线程将释放持有的锁. 锁只能由持有者释放, 如果线程
     *                       并不持有锁, 却执行该方法, 可能导致异常的发生.
     *     3. Condition newCondition()：条件对象，获取等待通知组件。该组件和当前的锁绑定，
     *                                  当前线程只有获取了锁，才能调用该组件的 await()方法，而调用后，当前线程将缩放锁。
     *     4. getHoldCount()：查询当前线程保持此锁的次数，也就是执行此线程执行 lock 方法的次数。
     *     5. getQueueLength()：返回正等待获取此锁的线程估计数。
     *                          比如启动 10 个线程，1 个线程获得锁，此时返回的是 9
     *     6. getWaitQueueLength：返回等待与此锁相关的给定条件的线程估计数。
     *                            比如 10 个线程，用同一个 condition 对象，并且此时这 10 个线程都执行了
     *                            condition 对象的 await 方法，那么此时执行此方法返回 10
     *     7. hasWaiters(Condition condition)：查询是否有线程等待与此锁有关的给定条件(condition)，
     *                                         对于指定 condition 对象，有多少线程执行了 condition.await 方法
     *     8. hasQueuedThread(Thread thread)：查询给定线程是否等待获取此锁
     *     9. hasQueuedThreads()：是否有线程等待此锁
     *     10. isFair()：该锁是否公平锁
     *     11. isHeldByCurrentThread()： 当前线程是否保持锁锁定，线程的执行 lock 方法的前后分别是 false 和 true
     *     12. isLock()：此锁是否有任意线程占用
     *     13. lockInterruptibly()：获取可中断的锁
     *                              一个线程调用lockInterruptibly()方法时，如果其他线程调用了该线程的interrupt()方法，
     *                              则lockInterruptibly()会抛出InterruptedException，从而允许线程响应中断。
     *     14. tryLock()：尝试获得锁，如果锁不可用, 不会导致当前线程被禁用,当前线程仍然继续往下执行代码.
     *     15. tryLock(long timeout TimeUnit unit)：如果锁在给定等待时间内没有被另一个线程持有，则获取该锁。
     */

    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Lock锁常用方法 example = new Lock锁常用方法();

        Thread thread = new Thread(() -> {
            try {
                example.doSomething();
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted!");
                // 线程可以选择退出或继续执行其他操作
                // throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                example.doSomething();
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted!");
                // 线程可以选择退出或继续执行其他操作
                // throw new RuntimeException(e);
            }
        });

        thread.start();
        thread2.start();

        // 让主线程休眠一会儿，以确保子线程已经开始执行
        Thread.sleep(100);

        // 中断子线程
        thread.interrupt();

        System.out.println("主线程执行完毕！！！");
    }

    public void doSomething() throws InterruptedException {
        // 获取锁，且支持中断锁
        lock.lockInterruptibly();
        try {
            // 执行一些需要锁的操作
            System.out.println(Thread.currentThread() + " -- 你好，这里做一些锁操作1");

            TimeUnit.SECONDS.sleep(2);

            // 这里并没有在控制台打印，说明已经被中断锁了
            System.out.println(Thread.currentThread() + " -- 你好，这里做一些锁操作2");
        } finally {
            lock.unlock(); // 确保释放锁
        }
    }
}
