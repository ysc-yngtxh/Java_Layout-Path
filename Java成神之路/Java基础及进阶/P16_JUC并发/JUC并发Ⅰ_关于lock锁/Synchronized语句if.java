package P16_JUC并发.JUC并发Ⅰ_关于lock锁;

public class Synchronized语句if{
    /**
     * 为什么在synchronized中使用wait、notify等关键字时，尽量不使用if作为判断语句的原因.
     *    如果在一if判断中去使用wait、notify，当前只有一两个线程去调用是没有什么问题，但是一旦很多线程去调用，那么就会出现错误(num值可能会超过1)。
     *    if判断中的wait、notify会存在一个虚假唤醒的问题，换句话说，等待应该总是出现在while循环中。可以参考类B
    */
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.demo2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }
}

class Data{

    int num=0;

    public synchronized void demo1() throws InterruptedException {
        if(num == 0){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "---num值---" + num);
        this.notifyAll();
    }

    public synchronized void demo2() throws InterruptedException {
        if(num != 0){
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "---num值---" + num);
        this.notifyAll();
    }
}
