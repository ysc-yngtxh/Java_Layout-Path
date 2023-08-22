package K11_线程.线程基础知识3;

public class 终止一个线程的执行4 {
    public static void main(String[] args) {
        MyT m = new MyT();
        Thread t = new Thread(m);
        t.setName("t");
        t.start();    // 代码执行到此处后，去执行MyT线程中方法，但是多线程不影响进度，所以他在执行MyT线程时候main线程继续往下走

        // 模拟5秒
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 终止程序
        m.run=false;
    }
}

class MyT implements Runnable{

    // 打一个布尔标记
    boolean run = true;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            if (run) {

                System.out.println(Thread.currentThread().getName() + "-->" + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {
                // return就结束了，你在结束之前还有什么没保存的，可以在这儿保存
                // save...

                return;
            }
        }
    }
}
