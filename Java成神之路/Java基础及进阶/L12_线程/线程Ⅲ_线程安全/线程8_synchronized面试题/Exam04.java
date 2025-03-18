package L12_线程.线程Ⅲ_线程安全.线程8_synchronized面试题;

/*
 * 面试题：
 *   类MyClass4 中 doSome()、doOther() 方法都有synchronized，并且是静态方法
 *   且在主方法中创建两个 MyClass4 对象，doOther() 方法的执行需不需要等 doSome() 方法执行结束？
 */
public class Exam04 {
    public static void main(String[] args) throws InterruptedException {
        MyClass4 mc4 = new MyClass4();
        MyClass4 mc44 = new MyClass4();

        Thread t1 = new MyThread4(mc4);
        Thread t2 = new MyThread4(mc44);

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        Thread.sleep(1000);  // 这个睡眠的作用是：为了保证1线程先执行
        t2.start();
    }
}
class MyThread4 extends Thread{
    private MyClass4 mc;
    public MyThread4(MyClass4 mc) {
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
class MyClass4 {
    public synchronized static void doSome() {
        System.out.println("doSome begin");
        try{
            Thread.sleep(1000*10);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("doSome over");
    }
    public synchronized static void doOther() {
        System.out.println("doOther begin");
        System.out.println("doOther over");
    }
}

// 需要，因为静态方法是类锁，类锁是不管创建几个对象，都只有一把
