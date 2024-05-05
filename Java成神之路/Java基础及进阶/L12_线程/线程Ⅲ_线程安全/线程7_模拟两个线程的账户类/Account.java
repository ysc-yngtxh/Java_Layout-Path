package L12_线程.线程Ⅲ_线程安全.线程7_模拟两个线程的账户类;

public class Account {
    private String action;   // 账号
    private double balance;  // 余额

    // 取款方法
    public boolean withdraw(double money) {
    /*
       public synchronized void withdraw(double money){}
       synchronized出现在实例方法上，表示整个方法体都需要同步，可能会无故扩大同步的范围，导致程序执行效率降低
       如果共享的对象就是this，并且需要同步的代码块是整个方法体，建议使用这种方式

       synchronized后面的小括号中传的这个"数据"是相当关键的。这个数据必须是多线程共享的数据。才能达到多线程排队

        ()中写什么？
            那要看你想让哪些线程同步
            假设t1,t2,t3,t4,t5有五个线程
            你只希望t1,t2,t3排队，t4,t5不需要排队，、
            你一定要在（）写一个t1,t2,t3共享的对象，而这个对象是对t4,t5不共享的

        synchronized（"abc")  "abc"在字符串常量池当中，所以是对所有线程共享的对象
    */
        synchronized (this) {
            double before = this.getBalance();
            double after;
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            if (before - money >= 0) {
                after = before - money;
                this.setBalance(after);
                return true;
            } else {
                after = 0.0;
                this.setBalance(after);
                return false;
            }
        }
    }

    public Account() {
    }

    public Account(String action, double balance) {
        this.action = action;
        this.balance = balance;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
 }