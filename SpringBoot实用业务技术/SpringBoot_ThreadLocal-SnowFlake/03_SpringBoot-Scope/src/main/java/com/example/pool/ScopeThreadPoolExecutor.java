package com.example.pool;

import com.example.context.Scope;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScopeThreadPoolExecutor extends ThreadPoolExecutor {

	ScopeThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime
			, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	public static ScopeThreadPoolExecutor newFixedThreadPool(int nThreads) {
		return new ScopeThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
		                                   new LinkedBlockingQueue<Runnable>());
	}

	/**
	 * 只要override重写这一个方法就可以
	 * 其他的submit, invokeAll等执行线程方法都会代理到这里来
	 */
	@Override
	public void execute(Runnable runnable) {
		Scope scope = Scope.getCurrentScope();
		// 提交任务时，把执行 execute 方法的线程中的 Scope 传进去
		super.execute(() -> Scope.supplyWithExistScope(scope, runnable));
	}
}
