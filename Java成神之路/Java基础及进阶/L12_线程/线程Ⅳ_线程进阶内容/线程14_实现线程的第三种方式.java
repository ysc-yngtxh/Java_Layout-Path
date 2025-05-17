package L12_线程.线程Ⅳ_线程进阶内容;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* 实现线程的第三种方式：
 *    FutureTask方式，实现Callable接口（JDK8新特性）
 *    这种方式的优点：可以获取到线程的执行结果
 *    这种方式的缺点：效率比较低，在获取t线程执行结果的时候，当前线程受阻塞，效率较低
 */
public class 线程14_实现线程的第三种方式 {

	public static void main(String[] args) throws Exception {
		// 第一步：创建一个“未来任务类”对象。
		FutureTask<Object> task = new FutureTask<>(new Callable<Object>() {
			@Override
			public Object call() throws Exception {  // Call方法就相当于run方法。只不过这个有返回值
				// 线程执行一个任务，执行之后可能会有一个执行结果
				// 模拟执行
				System.out.println("Call method begin");
				Thread.sleep(1000 * 10);
				System.out.println("Call method end");
				int a = 100;
				int b = 200;
				return a + b;   // 自动装箱（300结果变成Integer）
			}
		});

		// 创建线程对象
		Thread t = new Thread(task);
		t.start();

		// 这里是main方法，这是在主线程中
		// 获取Callable方法的返回值
		Object obj = task.get();   // get方法会让main方法进入阻塞状态
		System.out.println("线程执行结果：" + obj);

		// main方法这里的程序要想执行必须等待get()方法的结果
		// 而get()方法可能需要很久。因为get()方法是为了拿另一个线程的执行结果。但另一个线程执行是需要时间的。
		System.out.println("Hello World!");
	}

}
