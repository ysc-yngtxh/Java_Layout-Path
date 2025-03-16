package L12_线程.线程Ⅰ_实现线程;

/*
 * 实现线程的第一种方式：
 *     编写一个类，直接继承java.lang.Thread，重写run方法
 *
 *     怎么创建线程对象？new就行了
 *     怎么启动线程？调用线程对象的start()方法
 *
 * 注意：
 *     亘古不变的道理：
 *         方法体中的代码必须遵循自上而下的顺序依次逐行执行
 *
 * 以下程序的输出结果有这样的特点：
 *      有先有后
 *      有多有少
 */
class MyThread extends Thread {
    @Override
    public void run() {
        // 编写程序，这段程序运行在分支线程中
        for (int i = 0; i < 100; i++) {
            System.out.println("分支线程--->" + i);
        }
    }
}
public class 线程1_实现线程的第一种方式 {
    public static void main(String[] args) {
        // 这里是main方法，这里的代码属于主线程，在主栈中运行
        // 新建一个分支线程对象
        MyThread myThread = new MyThread();

        // 启动线程
        // start()方法的作用是,启动一个分支线程，在JVM中开辟一个新的栈空间。这段代 码任务完成之后，瞬间就结束了
        // 这段代码的任务只是为了开启一个新的栈空间，只要新的栈空间开出来，start()方法就结束了，线程就启动成功了
        // 启动成功的线程会自动调用run()方法，并且run()方法在分支栈的栈底部(压栈)
        // run方法在分支栈的栈底部，main方法在主栈的栈底部。run和main是平级的
        myThread.start();

        // myThread.run();   // 不会启动线程，不会分配新的分支栈(这种方式就是单线程)
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程 ---> " + i);
        }
    }
}
