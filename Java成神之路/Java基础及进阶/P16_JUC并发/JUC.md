## JUC简介：

    在 Java 5.0 提供了 java.util.concurrent(简称JUC)包,在此包中增加了在并发编程中很常用的工具类,
    用于定义类似于线程的自定义子系统,包括线程池、异步IO 和 轻量级任务框架;还提供了设计用于多线程上下文中的 Collection 实现等;

## volatile关键字

    volatile关键字: 当多个线程进行操作共享数据时,可以保证内存中的数据是可见的;相较于synchronized是一种较为轻量级的同步策略;
    volatile不具备"互斥性";
    volatile不能保证变量的"原子性";
    Java默认有几个线程？两个(main、GC)
    Java真的可以开启线程吗？开不了
    new Thread().start()
                  |
                  |
                 \|/
                start0()
                  |
                  |
                 \|/
    public native start0(){}  native是本地方法，底层C++，Java无法操作硬件
    并发：CPU一核，多线程操作统一资源
    并行：CPU多核，多个线程可以同时执行(线程池)
    wait(会释放锁)：wait必须在同步代码块中，因为需要一个线程等待
    sleep(不会释放锁)：sleep可以在任何地方
