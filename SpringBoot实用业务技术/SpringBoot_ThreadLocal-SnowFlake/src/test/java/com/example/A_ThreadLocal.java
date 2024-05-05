package com.example;

import com.example.vo.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class A_ThreadLocal {
    private final Logger log = LoggerFactory.getLogger(A_ThreadLocal.class);
    /**
     * ThreadLocal和线程同步机制相比有什么优势呢？ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题。
     *  1、在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。
     *    这时该变量是多个线程共享的，使用同步机制要求程序慎密地分析什么时候对变量进行读写，
     *    什么时候需要锁定某个对象，什么时候释放对象锁等繁杂的问题，程序设计和编写难度相对较大。
     *
     *  2、而ThreadLocal则从另一个角度来解决多线程的并发访问。
     *    ThreadLocal会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突。
     *    因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。
     *    ThreadLocal提供了线程安全的共享对象，在编写多线程代码时，可以把不安全的变量封装进ThreadLocal。
     *
     *  3、概括起来说，对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式。
     *    前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响。
     */

    private static final java.lang.ThreadLocal<User> THREAD_LOCAL = new java.lang.ThreadLocal<>();
    private static void setData(User user) {
        System.out.println("set数据，线程名：" + Thread.currentThread().getName());
    }

    private static void getAndPrintData() {
        // 拿到当前线程绑定的一个变量，然后做逻辑（本处只打印）
        User user = THREAD_LOCAL.get();
        System.out.println("get数据，线程名：" + Thread.currentThread().getName() + "，数据为：" + user);
    }

    @Test
    void contextLoads() throws InterruptedException {
        setData(new User());

        Runnable A = () -> {
            for (int i = 0; i < 5; i++) {
                // 方法入口处，设置一个变量和当前线程绑定
                setData(new User(i, "游诗成" + i));
                // 调用其它方法，其它方法内部也能获取到刚放进去的变量
                User user = THREAD_LOCAL.get();
                getAndPrintData();
                THREAD_LOCAL.remove();
                System.out.println("======== Finish" + i + " =========");

            }
        };
        Runnable B = () -> {
            for (int i = 0; i < 5; i++) {
                // 方法入口处，设置一个变量和当前线程绑定
                setData(new User(i, "曹玉敏" + i));
                // 调用其它方法，其它方法内部也能获取到刚放进去的变量
                getAndPrintData();
                THREAD_LOCAL.remove();
                System.out.println("======== Finish" + i + " =========");
            }
        };
        new Thread(A).start();
        new Thread(A).join();
        new Thread(B).start();
        new Thread(B).join();

        TimeUnit.SECONDS.sleep(6);
        getAndPrintData();
        THREAD_LOCAL.remove();

        // 可以发现对于User共享对象,多线程并发互不影响数据。不论线程A和线程B赋值多少次，在主线程中的user始终为空。
    }
}
