package L12_线程.线程Ⅲ_线程安全.线程8_synchronized面试题;

/*
 * 面试题：
 *   类MyClass3 中 doSome()、doOther() 方法都有synchronized，
 *   且在主方法中创建两个 MyClass3 对象，doOther() 方法的执行需不需要等 doSome() 方法执行结束？
 */
public class Exam03 {
    public static void main(String[] args) throws InterruptedException {
        MyClass3 mc3 = new MyClass3();
        MyClass3 mc33 = new MyClass3();

        Thread t1 = new MyThread3(mc3);
        Thread t2 = new MyThread3(mc33);

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        Thread.sleep(1000);  // 这个睡眠的作用是：为了保证1线程先执行
        t2.start();
    }
}
class MyThread3 extends Thread {
    private MyClass3 mc;
    public MyThread3(MyClass3 mc) {
        this.mc = mc;
    }
    public void run() {
        if(Thread.currentThread().getName().equals("t1")){
            mc.doSome();
        }
        if(Thread.currentThread().getName().equals("t2")){
            mc.doOther();
        }
    }
}
class MyClass3 {
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
