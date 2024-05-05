package P16_JUC并发.JUC并发Ⅰ_关于lock锁;

public class Synchronized语句while {
    /**
     * 你要知道wait会释放锁，所以会允许多个线程进入去修改共享数据的值。
     * 这不符合我们对程序要求，并且我们可以从官方文档中看到，建议使用while循环用以保证我们共享数据的安全性
     */
    public static void main(String[] args) {
        Datas data = new Datas();
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

class Datas {
    int num = 0;
    public synchronized void demo1() throws InterruptedException {
        while (num == 0){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "---num值---" + num);
        this.notifyAll();
    }
    public synchronized void demo2() throws InterruptedException {
        while(num != 0){
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "---num值---" + num);
        this.notifyAll();
    }
}