package L12_线程.线程Ⅲ_线程安全.线程8_synchronized面试题;

/*
 * 面试题：
 *   类MyClass2 中 doSome()、doOther() 方法都有synchronized
 *   且在主方法中只创建 MyClass2 一个对象，doOther() 方法的执行需不需要等 doSome() 方法执行结束？
 */
public class Exam02 {
    public static void main(String[] args) throws InterruptedException {
        MyClass2 mc = new MyClass2();

        Thread t1 = new MyThread2(mc);
        Thread t2 = new MyThread2(mc);

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        Thread.sleep(1000);  // 这个睡眠的作用是：为了保证1线程先执行
        t2.start();
    }
}
class MyThread2 extends Thread {
    private MyClass2 mc;
    public MyThread2(MyClass2 mc) {
        this.mc = mc;
    }
    public void run() {
        if(Thread.currentThread().getName().equals("t1")) {
            mc.doSome();
        }
        if(Thread.currentThread().getName().equals("t2")) {
            mc.doOther();
        }
    }
}
class MyClass2 {
    public synchronized void doSome() {
        System.out.println("doSome begin");
        try {
            Thread.sleep(1000*10);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("doSome over");
    }
    public synchronized void doOther() {
        System.out.println("doOther begin");
        System.out.println("doOther over");
    }
}

// 需要，因为在方法上都添加synchronized关键字，实现的是方法锁(也叫对象锁)，共享对象是this。所以同一个对象多线程执行会被阻塞。
// 对象锁和类锁是两种不同的锁机制，它们不会互相干扰。一个线程可以持有多个对象锁，但只能持有一个类锁。
