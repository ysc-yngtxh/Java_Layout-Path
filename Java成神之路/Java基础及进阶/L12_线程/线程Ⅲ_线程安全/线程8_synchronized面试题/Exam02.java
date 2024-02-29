package L12_线程.线程Ⅲ_线程安全.线程8_synchronized面试题;

/*
  面试题：
    MyClass中两方法都有synchronized
    doOther方法的执行需不需要等doSome方法的结束？
 */
public class Exam02 {
    public static void main(String[] args) throws InterruptedException {
        MyClass1 mc1 = new MyClass1();

        Thread t1 = new MyThread1(mc1);
        Thread t2 = new MyThread1(mc1);

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        Thread.sleep(1000);  // 这个睡眠的作用是：为了保证1线程先执行
        t2.start();
    }
}
class MyThread1 extends Thread{
    private MyClass1 mc1;
    public MyThread1(MyClass1 mc1){
        this.mc1 = mc1;
    }
    public void run(){
        if(Thread.currentThread().getName().equals("t1")){
            mc1.doSome();
        }
        if(Thread.currentThread().getName().equals("t2")){
            mc1.doOther();
        }
    }
}
class MyClass1 {
    public synchronized void doSome(){
        System.out.println("doSome begin");
        try{
            Thread.sleep(1000*10);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("doSome over");
    }
    public synchronized void doOther(){
        System.out.println("doOther begin");
        System.out.println("doOther over");
    }
}

// 需要，因为在方法上都添加synchronized关键字，实现的是方法锁(也叫对象锁)，共享对象是this。所以同一个对象多线程执行会被阻塞。
// 对象锁和类锁是两种不同的锁机制，它们不会互相干扰。一个线程可以持有多个对象锁，但只能持有一个类锁。
