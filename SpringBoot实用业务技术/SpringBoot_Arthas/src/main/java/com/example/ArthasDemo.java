package com.example;

import lombok.SneakyThrows;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * @author 游家纨绔
 * @dateTime 2023-09-17 18:00
 * @apiNote TODO
 */
public class ArthasDemo {

    @SneakyThrows
    public static void main(String[] args) {
        ArthasDemo demo = new ArthasDemo();
        // demo.justRun();
        // demo.seeThread();
        demo.seeProductionCode();
        // demo.printCarInfo();
        // demo.deadLoop();
        // demo.deadLock();
    }

    @SneakyThrows
    private void justRun() {
        while (Instant.now().isBefore(Instant.now().plus(1, ChronoUnit.DAYS))) {
            System.out.println("running");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @SneakyThrows
    private void seeThread() {
        Thread thread = new Thread(() -> {
            System.out.println("this is in a thread");
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "THREAD_DEMO");
        thread.start();
    }

    /**
     * 查看生产的代码（反编译）
     */
    @SneakyThrows
    void seeProductionCode() {
        // 代码没执行是没有提交吗？还是分支搞错了？
        // jad
        // 使用 arthas 替换 class，实现热部署
        GoodHabit goodHabit = new GoodHabit();
        goodHabit.doSomething();
        TimeUnit.HOURS.sleep(1);
    }

    Car getCar(String carName, long carPrice) {
        Car car = new Car();
        car.setName(carName);
        car.setPrice(carPrice);
        return car;
    }
    @SneakyThrows
    void printCarInfo() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(getCar("catName-" + i, Long.parseLong(i+"")));
            TimeUnit.SECONDS.sleep(1);
        }
    }

    /**
     * 死循环
     */
    private void deadLoop() {
        while (true) {
            System.out.println("this is in dead loop");
        }
    }


    private static final String A = "A";
    private static final String B = "B";
    private void deadLock() {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                System.out.println("线程一获取到资源A");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // ...
                }
                System.out.println("线程一尝试获取资源B");
                synchronized (B) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        // ...
                    }
                }
            }
        }, "死锁一号");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                System.out.println("线程二获取到资源B");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // ...
                }
                System.out.println("线程二尝试获取资源A");
                synchronized (A) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        // ...
                    }
                }
            }
        }, "死锁二号");
        t2.start();
    }
}