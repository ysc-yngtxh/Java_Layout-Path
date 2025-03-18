package L12_线程.线程Ⅲ_线程安全.线程8_synchronized面试题;

/*
 * 面试题：
 *   类MyClass 中 doSome() 方法有synchronized，doOther() 方法没有synchronized
 *   且在主方法中只创建 MyClass 一个对象，那么 doOther() 方法的执行需不需要等 doSome() 方法执行结束嘛？
 */
public class Exam01 {
    public static void main(String[] args) throws InterruptedException {
        MyClass mc = new MyClass();
        Thread t1 = new MyThread(mc);
        Thread t2 = new MyThread(mc);

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        Thread.sleep(1000);  // 这个睡眠的作用是：为了保证 t1线程 先执行
        t2.start();
    }
}
class MyThread extends Thread {
    private MyClass mc;
    public MyThread(MyClass mc) {
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
class MyClass {
    public synchronized void doSome() {
        System.out.println("doSome begin");
        try {
            Thread.sleep(1000*10);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("doSome over");
    }
    public void doOther() {
        System.out.println("doOther begin");
        System.out.println("doOther over");
    }
}

// 不需要，只有 t1线程 在执行synchronized方法期间持有对象锁，而 t2线程 方法没有synchronized并不需要获取对象锁，所以不会被阻塞。
