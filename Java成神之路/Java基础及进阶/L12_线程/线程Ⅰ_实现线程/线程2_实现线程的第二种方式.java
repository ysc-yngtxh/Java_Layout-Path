package L12_线程.线程Ⅰ_实现线程;

/*
 * 实现线程的第二种方式：编写一个类实现java.lang.Runnable接口
 *   注意：
 *       第二种方式实现接口比较常用，因为是面向接口编程，一个类实现了接口，它还可以去继承其他的类，更灵活
 */
class MyRunnable implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("分支线程1 ---> " + i);
        }
    }
}
public class 线程2_实现线程的第二种方式 {
    public static void main(String[] args) {
        // 创建一个可运行的对象
        // MyRunnable r = new MyRunnable();

        // 将可运行的对象封装成一个线程对象
        Thread t1 = new Thread(new MyRunnable());

        // 启动线程
        t1.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程1 ---> " + i);
        }

        // 使用匿名内部类实现线程
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("分支线程2 ---> " + i);
                }
            }
        });
        t2.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程2 ---> " + i);
        }
    }
}
