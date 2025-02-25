package com.example;

import com.example.context.Scope;
import com.example.context.ScopeKey;
import com.example.pool.ScopeThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-26 15:29
 * @apiNote TODO 线程池测试示例
 */
@Slf4j
@SpringBootTest
public class C_ThreadPoolThreadLocal {

    // TODO 1、现在需要实现这样一个功能，在一个线程执行过程中开启了一个Scope，随后使用线程池执行任务，
    //         要求在线程池中也能获取当前Scope中的状态数据。
    //      2、典型的使用场景是：服务收到一个用户请求，通过Scope将登陆态数据存到当前线程的上下文中，
    //                        随后使用线程池执行一些耗时的操作，希望线程池中的线程也能拿到Scope中的登陆态数据。
    //      3、由于线程池中的线程和请求线程不是一个线程，按照目前的实现，线程池中的线程是无法拿到请求线程上下文中的数据的。
    //      4、解决方法：在提交 runnable 时，将当前的 Scope引用 存到 runnable对象中，
    //                 当获得线程执行时，将 Scope 替换到执行线程中，执行完成后，再恢复现场。
    @Test
    public void testScopeThreadPoolExecutor() {
        ScopeKey<String> localVariable = new ScopeKey<>();
        Scope.beginScope();

        try {
            localVariable.set("value out of thread pool");
            Runnable r = () -> log.info("localVariable in thread pool: {}", localVariable.get());

            // 使用线程池执行，能获取到外部Scope中的数据。实际上也是获取的主线程中的Scope，进而获取数据状态
            ExecutorService executor = ScopeThreadPoolExecutor.newFixedThreadPool(10);
            executor.execute(r);
            executor.submit(r);
        } finally {
            Scope.endScope();
        }
    }
}
