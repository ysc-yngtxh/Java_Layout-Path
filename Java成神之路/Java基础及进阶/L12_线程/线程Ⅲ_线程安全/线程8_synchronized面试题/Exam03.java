package L12_线程.线程Ⅲ_线程安全.线程8_synchronized面试题;

/*
 * 面试题：
 *   MyClass中两方法都有synchronized，且，在主方法中创建两个MyClass2对象
 *   doOther方法的执行需不需要等doSome方法的结束？
 */
public class Exam03 {
    public static void main(String[] args) throws InterruptedException {
        MyClass2 mc2 = new MyClass2();
        MyClass2 mc3 = new MyClass2();

        Thread t1 = new MyThread2(mc2);
        Thread t2 = new MyThread2(mc3);

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        Thread.sleep(1000);  // 这个睡眠的作用是：为了保证1线程先执行
        t2.start();
    }
}
class MyThread2 extends Thread {
    private MyClass2 mc2;
    public MyThread2(MyClass2 m) {
        this.mc2 = mc2;
    }
    public void run() {
        if(Thread.currentThread().getName().equals("t1")){
            mc2.doSome();
        }
        if(Thread.currentThread().getName().equals("t2")){
            mc2.doOther();
        }
    }
}
class MyClass2 {
    public synchronized void doSome() {
        System.out.println("doSome begin");
        try{
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

// 不需要，虽然MyClass中实现的对象锁，但是没有共享的对象，谁也不影响谁
