package L12_线程.线程Ⅲ_线程安全.线程7_模拟两个线程的账户类;

public class AccountThread extends Thread {
    // 两个线程必须共享同一个账户对象
    private Account act;

    // 通过构造方法传递过来账户对象
    public AccountThread(Account act){
        this.act = act;
    }

    public void run(){
        // run方法的执行表示取款操作
        // 假设取款5000
        double money = 5000;
        // 取款
        boolean few = act.withdraw(money);
        String success = "账户" + act.getAction() + "取款" + money + "成功，剩余金额" + act.getBalance();
        String fail = "账户" + act.getAction() + "取款" + money + "失败，剩余金额" + act.getBalance();
        if (few) {
            System.out.println(success);
        } else {
            System.err.println(fail);
        }
    }
}