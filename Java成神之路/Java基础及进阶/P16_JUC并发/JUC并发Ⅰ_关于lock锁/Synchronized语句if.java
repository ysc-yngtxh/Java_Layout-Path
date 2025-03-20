package P16_JUC并发.JUC并发Ⅰ_关于lock锁;

public class Synchronized语句if{
    /**
     * 为什么在synchronized中使用wait、notify等关键字时，尽量不使用if作为判断语句
     *    1、如果在一if判断中去使用wait、notify，当前只有一两个线程去调用是没有什么问题，
     *       但是一旦很多线程去调用，那么就会出现跟我们预期结果相悖的错误(num<0或者num>1)。
     *    2、正常情况下当我们在synchronized块中使用 wait() 方法时，它会释放当前线程持有的对象锁，并使得当前线程进入该对象的等待锁定池
     *       直到其他线程调用了该对象的 notify() 或 notifyAll() 方法，该线程才会进入对象锁定池准备获得对象锁并进入运行状态。
     *    3、而我们去使用if判断中的wait、notify就会存在一个虚假唤醒的问题：
     *       比如：A线程在if判断(num == 0)为true，执行wait方法休眠等待，并且wait()方法会释放对象锁。
     *            C线程在if判断(num == 0)为true，执行wait方法休眠等待，并且wait()方法会释放对象锁。
     *            此时，B线程在if判断(num != 0)为false，执行 num++ 与 notifyAll()方法，num=1;
     *            A、C线程都被唤醒了，且两个线程都已经执行过if判断了，只会往下都去执行 num-- 操作，最终num=-1;
     *
     * 虚假唤醒：就是用 if 判断的话，唤醒后线程会从 wait 之后的代码开始运行，但是不会重新判断 if 条件，
     *         而是直接继续运行 if 代码块之后的代码。就会造成一个
     *         而如果使用 while 的话，也会从 wait 之后的代码运行，但是唤醒后会重新判断循环条件，
     *         如果不成立再执行 while 代码块之后的代码块，成立的话继续 wait
    */
    public static void main(String[] args) {
        Data data = new Data();
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
                    data.demo1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
class Data{
    volatile int num = 0;
    public synchronized void demo1() throws InterruptedException {
        if(num == 0) {
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "---num值===" + num);
        this.notifyAll();
    }
    public synchronized void demo2() throws InterruptedException {
        if(num != 0) {
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "---num值===" + num);
        this.notifyAll();
    }
}
