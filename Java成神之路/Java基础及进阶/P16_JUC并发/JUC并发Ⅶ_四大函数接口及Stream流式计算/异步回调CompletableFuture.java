package P16_JUC并发.JUC并发Ⅶ_四大函数接口及Stream流式计算;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("CallToPrintStackTrace")
public class 异步回调CompletableFuture {
    /**
     * 异步调用：CompletableFuture
     *    异步调用：异步程序没执行完不影响其他程序的执行！！
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // TODO 一、创建异步任务
        System.out.println("================================一、创建异步任务==================================");
        /**
         * runAsync() 是创建没有返回值的异步任务。
         * 它有两个方法，一个是使用默认线程池（ForkJoinPool.commonPool()）的方法(如下)，一个是带有自定义线程池的重载方法
         */
        CompletableFuture<Void> cfRunAsync1 = CompletableFuture.runAsync( () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ---- runAsync1 => Void");
        });
        System.out.println("CompletableFuture并不会阻塞当前线程，会先执行吗？");
        // 等待任务的get()方法执行完成: 如果完成则返回结果，否则就抛出具体的异常
        System.out.println("runAsync方法使用默认线程池的结果 -> " + cfRunAsync1.get() + " -- 很明显无返回值");
        // get(long timeout, TimeUnit unit): 最大时间等待返回结果，否则就抛出具体异常
        System.out.println( cfRunAsync1.get(1, TimeUnit.SECONDS) );

		// 自定义线程池
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		CompletableFuture<Void> cfRunAsync2 = CompletableFuture.runAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " ---- runAsync2 => Void");
		}, executorService);
		// 等待任务执行完成 get(): 如果完成则返回结果，否则就抛出具体的异常
		System.out.println("runAsync方法使用自定义线程池的结果 -> " + cfRunAsync2.get() + " -- 很明显无返回值");
		// cfRunAsync2.get(long timeout, TimeUnit unit): 最大时间等待返回结果，否则就抛出具体异常
		System.out.println("runAsync方法使用默认线程池在 1 秒时间内等待得到的结果 -> " + cfRunAsync2.get(1, TimeUnit.SECONDS));
		// 用完线程池记得关闭
		executorService.shutdown();

		/**
		 * supplyAsync 是创建带有返回值的异步任务。
		 * 它有两个方法，一个是使用默认线程池（ForkJoinPool.commonPool()）的方法(如下)，一个是带有自定义线程池的重载方法
		 */
		CompletableFuture<Integer> cfSupplyAsync = CompletableFuture.supplyAsync(() -> {
			System.out.println("Hello World!");
			return 2021;
		});
		// get(): 如果完成则返回结果，否则就抛出具体的异常
		System.out.println("supplyAsync方法使用默认线程池的结果 -> " + cfSupplyAsync.get());
		// cfSupplyAsync.get(long timeout, TimeUnit unit): 最大时间等待返回结果，否则就抛出具体异常
		System.out.println("supplyAsync方法使用默认线程池在 1 秒时间内等待得到的结果 -> " + cfSupplyAsync.get(1, TimeUnit.SECONDS));

		// TODO 二、异步回调处理
		System.out.println("================================二、异步回调处理==================================");
		/**
		 * thenApply 表示某个任务执行完成后执行的动作，即回调方法，会将该任务的执行结果即方法返回值作为入参传递到回调方法中，带有返回值。
		 */
		CompletableFuture<Integer> cf1 = cfSupplyAsync.thenApply((result) -> {
			System.out.println("上个任务结果result：" + result);
			return 2023;
		});
		System.out.println(cf1.get());

		/**
		 * whenComplete 是当某个任务执行完成后执行的回调方法，会将执行结果或者执行期间抛出的异常传递给回调方法，
		 * 如果任务是正常执行则传递的异常为null，任务执行的返回值则为回调方法参数
		 * 同样如果该回调方法正常执行，则get方法返回执行结果，如果是执行异常，则get方法抛出异常。
		 */
		CompletableFuture<Integer> cfSupplyAsync1 = CompletableFuture.supplyAsync(() -> {
			System.out.println("===执行有异常的异步回调===");
			int i = 10 / 0;
			return 2021;
		}).whenComplete((result, error) -> { // whenComplete回调方法，对原任务执行的结果进行操作，不创建新对象
			// result：表示执行成功的返回值  error：表示执行失败的报错信息
			System.out.println("上个任务结果result：" + result);
			System.out.println("上个任务抛出异常e：" + error);
		});

		CompletableFuture<Integer> cf2 = cfSupplyAsync1.whenComplete((result, error) -> {
			// result：表示执行成功的返回值  error：表示执行失败的报错信息
			System.out.println("上个任务结果result：" + result);
			System.out.println("上个任务抛出异常e：" + error);
		}).exceptionally((e) -> { // exceptionally返回一个新的CompletableFuture对象
			// exceptionally中可指定默认返回结果，如果出现异常，则返回默认的返回结果。它的作用相当于catch。
			System.out.println(e.getMessage());
			return 2333;
		});
		System.out.println(cf2.get());


		CompletableFuture<List<String>> future = CompletableFuture.supplyAsync(() -> {
			List<String> list = new ArrayList<>();
			list.add("语文");
			list.add("数学");
			return list;
		});
		// 使用 handle() 方法接收list数据和error异常，并且产生一个新的CompletableFuture对象
		CompletableFuture<Integer> future2 = future.handle((list, error) -> {
			// 如果报错，就打印出异常
			Optional.ofNullable(error).ifPresent(e -> System.out.println(error.getMessage()));
			// 如果不报错，返回一个包含Integer的全新的CompletableFuture
			return list.size();
			// 注意这里的两个CompletableFuture包含的返回类型不同
		});
		System.out.println(future2.get());

		// TODO 三、多任务组合处理
		System.out.println("================================三、多任务组合处理==================================");
		/**
		 * thenCombine、thenAcceptBoth 和runAfterBoth
		 * thenCombine：会将两个任务的执行结果作为所提供函数的参数，且该方法有返回值；
		 * thenAcceptBoth：同样将两个任务的执行结果作为方法入参，但是无返回值；
		 * runAfterBoth：没有入参，也没有返回值。注意两个任务中只要有一个执行异常，则将该异常信息作为指定任务的执行结果。
		 */
		CompletableFuture<Integer> supplyAsync1 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " supplyAsync1 do something....");
			return 1;
		});

		CompletableFuture<Integer> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " supplyAsync2 do something....");
			return 2;
		});

		CompletableFuture<Integer> thenCombine = supplyAsync1.thenCombine(supplyAsync2, (a, b) -> {
			System.out.println(Thread.currentThread().getName() + " -- thenCombine do something....");
			return a + b;
		});
		System.out.println("thenCombine结果 -> " + thenCombine.get());

		/**
		 * applyToEither、acceptEither和runAfterEither
		 * applyToEither：会将先完成任务的执行结果作为所提供函数的参数，后执行完的CompletableFuture结果不管，且该方法有返回值；
		 * acceptEither：同样将先完成任务的执行结果作为方法入参，但是无返回值；
		 * runAfterEither：没有入参，也没有返回值。
		 */
		CompletableFuture<String> applyToEither = supplyAsync1.applyToEither(supplyAsync2, (result) -> {
			System.out.println("接收到的值：" + result);
			System.out.println(Thread.currentThread().getName() + " -- applyToEither do something....");
			return "applyToEither 任务完成";
		});

		System.out.println("applyToEither结果 -> " + applyToEither.get());
	}

}
