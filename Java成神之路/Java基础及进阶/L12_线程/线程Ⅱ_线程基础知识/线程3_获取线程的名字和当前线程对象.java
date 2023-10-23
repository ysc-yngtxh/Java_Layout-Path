package L12_线程.线程Ⅱ_线程基础知识;

/*
  1、怎么获取当前线程对象？
       Thread t = Thread.currentThread();
       返回值t就是当前线程

  2、获取线程对象的名字
       String name = 线程对象.getName();

  3、修改线程对象的名字
       线程对象.setName("线程名字");

  4、当线程没有设置名字的时候，默认的名字有什么规律？（了解一下）
        Thread-0
        Thread-1
        Thread-2
        Thread-3
        Thread-4
        Thread-5
        ...
 */
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            // currentThread 就是当前线程对象
            // 当m线程执行run方法，那么这个当前线程就是 m
            // 当mf线程执行run方法，那么这个当前线程就是 mf
            Thread c = Thread.currentThread();
            System.out.println(c.getName() + "-->" + i);
        }
    }
}
public class 线程3_获取线程的名字和当前线程对象 {
    public static void main(String[] args) {
        // 这个代码出现在main方法当中，所以当前线程就是主线程
        Thread t = Thread.currentThread();
        System.out.println(t.getName());

        // 创建线程对象
        MyThread m = new MyThread();

        // 设置线程名字
        m.setName("m1");

        // 获取线程名字
        System.out.println(m.getName());

        // 启动线程
        m.start();

        MyThread mf = new MyThread();
        mf.setName("m2");
        System.out.println(mf.getName());
        mf.start();
    }
}