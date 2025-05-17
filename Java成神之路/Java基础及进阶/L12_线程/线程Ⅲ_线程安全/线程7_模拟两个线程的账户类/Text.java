package L12_线程.线程Ⅲ_线程安全.线程7_模拟两个线程的账户类;

public class Text {

	public static void main(String[] args) {
		// 创建账户对象（只能创建一个）
		Account act = new Account("act-001", 10000);
		// 创建三个线程
		Thread t1 = new AccountThread(act);
		Thread t2 = new AccountThread(act);
		Thread t3 = new AccountThread(act);

		// 设置name
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");

		// 启动线程取款
		t1.start();
		t2.start();
		t3.start();

		// t3线程 与 t1、t2线程 账户对象不同。所以，在执行 synchronized 时不与其t1、t2共享对象
		Account act1 = new Account("act-002", 50000);
		Thread t4 = new AccountThread(act1);
		t4.setName("t4");
		t4.start();
	}

}
