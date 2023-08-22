package K11线程.线程基础知识3;

/*
关于现成的调度（这部分内容属于了解）
    1、常见的线程调度模型有哪些?
         抢占式调度模型：
             哪个线程的优先级比较高，抢到的CPU时间片的效率就高一些/多一些

         均分是调度模型：
             平均分配CPU时间片，每个线程占有的CPU时间片时间长度一样。平均分配，一切平等。
             有一些编程语言，线程调度模型采用的是这种方式

    2、Java中提供了哪些方法是和线程调度有关系的？
         实例方法：
            void setPriority(int newPriority) 设置线程的优先级
            int getPriority() 获取线程优先级
            最低优先级1
            默认优先级5
            最高优先级10
            优先级比较高的获取CPU时间片可能会多一些。（但也不完全是，大概率是高的）

         静态方法：
             static void yield()  让位方法
             暂停当前正在执行的线程对象，并执行其他线程
             yield()方法不是阻塞方法。让当前线程让位，让给其他线程使用
             yield()方法的执行会让当前线程从“运行状态”回到“就绪状态”
             注意：在回到就绪之后，有可能还会再次抢到

         实例方法：
             void join()  插队
             合并线程
             class MyThread1 extends Thread{
                public void doSome(){
                   MyThread2 t = new MyThread()
                   t.join();//t线程先执行，直到t线程结束，主线程才可以继续。
                }
              }

              class MyThread2 extends Thread{
              }
 */
public class 线程调度方法 {
    public static void main(String[] args) {

        Thread t = new Thread(new YSC());
        t.setName("t");
        t.start();
        t.setPriority(10);  // 优先级较高的，只是抢到的CPU时间片相对多一些。大概率方向更偏向优先级比较高的

        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        }

        try {
            t.join();      // t线程先执行，直到t线程结束，主线程才可以继续。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over!");  // 所以这条语句是最后执行的
    }
}

class YSC implements Runnable{
    @Override
    public void run() {

        // 默认的优先级是5
        for (int i = 1; i <= 100; i++) {

            // 每10个让位一次
            if(i % 10 == 0){
                Thread.yield();   // 当前线程暂停一下，让给主线程
                // 比如在t-->29后，执行的就是main线程
            }
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
}
