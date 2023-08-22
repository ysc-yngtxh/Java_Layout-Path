package K11_线程.实现线程2;

public class 使用匿名内部类实现线程第二种方式3 {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("分支线程--->" + i);
                }
            }
        });

        t.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程--->" + i);
        }
    }
}
