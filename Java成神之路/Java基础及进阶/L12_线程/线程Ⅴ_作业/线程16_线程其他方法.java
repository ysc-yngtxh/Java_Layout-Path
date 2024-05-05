package L12_线程.线程Ⅴ_作业;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-10 16:13
 * @apiNote TODO
 */
public class 线程16_线程其他方法 {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }, "A");
        thread.start();

        // isAlive() 判断一个线程是否存活
        System.out.println("判断demo线程是否存活：" + thread.isAlive());

        // activeCount() 程序中活跃的线程数
        System.out.println("程序中活跃的线程数：" + Thread.activeCount());

        // enumerate() 枚举程序中的线程
        Thread[] thread1 = new Thread[Thread.activeCount()];
        System.out.println("枚举程序中的线程：" + Thread.enumerate(thread1));

        // isDaemon() 判断是否为守护线程
        System.out.println("判断是否为守护线程：" + thread.isDaemon());

    }
}
