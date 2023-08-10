package O15JUC进阶理解.G四大函数接口及Stream流式计算;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class 异步回调CompletableFuture {
    /**
     * 异步调用：CompletableFuture
     *    异步调用：异步程序没执行完不影响其他程序的执行！！
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 没有返回值的runAsync异步回调Ajax
         */
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" ---- runAsync ==> Void");
        });

        System.out.println("11111");

        completableFuture.get();  // 获取阻塞执行结果

        /**
         * 有返回值的supplyAsync异步回调 Ajax，
         * 成功和失败的回调
         * 返回的是错误信息
        */
        CompletableFuture<Integer> completableFuture1 =CompletableFuture.supplyAsync(()->{
            System.out.println("Hello World!");
            int i= 10/0;
            return 2021;
        });

        completableFuture1.whenComplete( (t,u)->{
            System.out.println("t=>"+t);
            System.out.println("u=>"+u);
        }).exceptionally( (e)->{
            System.out.println(e.getMessage());
            return 233;
        }).get();
    }
}
