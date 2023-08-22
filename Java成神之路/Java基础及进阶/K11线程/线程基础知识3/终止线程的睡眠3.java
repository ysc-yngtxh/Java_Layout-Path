package K11线程.线程基础知识3;

/*
sleep睡眠太久了，如果希望半道上醒来，怎么叫醒一个正在睡眠的线程？
       注意：这个不是中断线程的执行，是终止线程的睡眠
 */
public class 终止线程的睡眠3 {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable2());
        t.setName("t");
        t.start();

        // 希望5秒之后，t线程醒来
        try{
            Thread.sleep(1000*5);
        } catch(InterruptedException e1){
            e1.printStackTrace();
        }
        //中断t线程的睡眠
        t.interrupt();
    }
}

class MyRunnable2 implements Runnable{
    // 重点：run()当中的异常不能throw，只能try catch
    // 因为run()方法在父类中没有抛出任何异常，子类不能比父类抛出更多的异常
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + "-->begin");
        try{
            // 睡眠一年
            Thread.sleep(1000*60*60*24*365);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        // 一年后才会执行在这儿
        System.out.println(Thread.currentThread().getName() + "-->end");
    }
}