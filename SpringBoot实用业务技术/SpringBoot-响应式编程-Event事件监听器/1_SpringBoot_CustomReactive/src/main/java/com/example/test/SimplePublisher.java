package com.example.test;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Flow;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-25 20:16
 * @apiNote TODO 自定义发布者
 */
public class SimplePublisher<T> implements Flow.Publisher<T>, AutoCloseable {
    private static final Executor ASYNC_POOL =
            (ForkJoinPool.getCommonPoolParallelism() > 1) ?
                    ForkJoinPool.commonPool() : new SimplePublisher.ThreadPerTaskExecutor();

    private static final class ThreadPerTaskExecutor implements Executor {
        ThreadPerTaskExecutor() {
        }

        public void execute(Runnable r) {
            new Thread(r).start();
        }
    }

    // 发布者主题
    SimpleSubscription<T> clients;
    final ReentrantLock lock;
    // 是否关闭发布者标识
    volatile boolean isClose;
    // 是否订阅标识
    boolean isSubscriber;
    Thread owner;
    volatile Throwable closedException;
    final Executor executor;
    final int maxBufferCapacity;
    // 初始缓冲区容量，即发布者能发布消息的上限
    static final int INITIAL_CAPACITY = 32;

    // 有参构造方法
    public SimplePublisher(Executor executor, int maxBufferCapacity) {
        if (executor == null)
            throw new NullPointerException();
        if (maxBufferCapacity <= 0)
            throw new IllegalArgumentException("capacity must be positive");
        this.lock = new ReentrantLock();
        this.executor = executor;
        this.maxBufferCapacity = maxBufferCapacity;
    }

    // 无参构造方法
    public SimplePublisher() {
        this(ASYNC_POOL, Flow.defaultBufferSize());
    }

    // 发布者订阅方法
    @Override
    public void subscribe(Flow.Subscriber subscriber) {
        if (subscriber == null) throw new NullPointerException();
        ReentrantLock lock = this.lock;
        int max = maxBufferCapacity;
        // 取较小值作为数组容量
        AtomicReference<Object[]> array = new AtomicReference<>(new Object[Math.min(max, INITIAL_CAPACITY)]);
        // 创建订阅主题
        SimpleSubscription<T> subscription =
                new SimpleSubscription<T>(subscriber, executor, array, max);
        lock.lock();
        try {
            // 判断是否已经被订阅。如果没有，则将订阅标识标记为true，并且记录第一个订阅者的线程赋值给成员变量owner
            if (!isSubscriber) {
                isSubscriber = true;
                owner = Thread.currentThread();
            }
            // 相当于 SimpleSubscription<T> b = clients；SimpleSubscription<T> pred = null
            // for循环没有后面两条件，会一直循环执行，直至break退出循环
            for (SimpleSubscription<T> b = clients, pred = null; ; ) {
                // 成员变量clients也只是一个引用，相当于将引用赋值给b。所以进入该if判断只能是第一个订阅者
                if (b == null) {
                    // Throwable ex;
                    subscription.onStart();
                    // if ((ex = closedException) != null)
                    //     subscription.onError(ex);
                    // 是否关闭发布者
                    if (isClose)
                        subscription.onNone();
                    else if (pred == null)
                        clients = subscription;
                    else
                        pred.next = subscription;
                    break;
                }
                SimpleSubscription<T> next = b.next;
                if ((b.ctl.get() & SimpleSubscription.CLOSED) != 0) {   // remove
                    b.next = null;    // detach
                    if (pred == null)
                        clients = next;
                    else
                        pred.next = next;
                } else if (subscriber.equals(b.subscriber)) {
                    b.onException(new IllegalStateException("Duplicate subscribe"));
                    break;
                } else
                    pred = b;
                b = next;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void close() {
        ReentrantLock lock = this.lock;
        if (!isClose) {
            SimpleSubscription<T> b;
            lock.lock();
            try {
                // 无需重新检查此处已关闭
                b = clients;
                clients = null;
                owner = null;
                isClose = true;
            } finally {
                lock.unlock();
            }
            while (b != null) {
                SimpleSubscription<T> next = b.next;
                b.next = null;
                b.onNone();
                b = next;
            }
        }
    }

    // 相当于主题。Publisher发布者发布一个或多个Subscription主题，每个主题被Subscriber订阅
    static class SimpleSubscription<T> implements Flow.Subscription {
        long timeout;                      // Long.MAX_VALUE if untimed wait
        int head;                          // next position to take
        int tail;                          // next position to put
        final int maxCapacity;             // max buffer size
        AtomicInteger ctl = new AtomicInteger(0);   // ctl是一个位掩码变量，原子运行状态标志
        AtomicReference<Object[]> array;
        final Flow.Subscriber<? super T> subscriber;
        Executor executor;
        Thread waiter;
        Throwable pendingError;
        SimpleSubscription<T> next;
        SimpleSubscription<T> nextRetry;

        AtomicLong demand = new AtomicLong(0);
        AtomicInteger waiting = new AtomicInteger(0);

        // ctl bit values
        static final int CLOSED = 0x01;  // 1 如果设置，则忽略其他位
        static final int ACTIVE = 0x02;  // 2 消费者任务的保持活动状态
        static final int REQS = 0x04;    // 4 非零消费请求 [request方法]
        static final int ERROR = 0x08;   // 8 注意到错误时出现问题
        static final int COMPLETE = 0x10;  // 16 完成后出现 onComplete 问题
        static final int RUN = 0x20;   // 32 任务正在或将要运行
        static final int OPEN = 0x40;  // 64 订阅后为真

        SimpleSubscription(Flow.Subscriber<? super T> subscriber, Executor executor,
                           AtomicReference<Object[]> array, int maxBufferCapacity) {
            this.subscriber = subscriber;
            this.executor = executor;
            this.array = array;
            this.maxCapacity = maxBufferCapacity;
        }

        @Override
        public void request(long n) {
            if (n > 0L) {
                for (;;) {
                    long p = demand.get(), d = p + n;  // saturate
                    if (demand.weakCompareAndSetPlain(p, d < p ? Long.MAX_VALUE : d))
                        break;
                }
                startOnSignal(RUN | ACTIVE | REQS);
            }
            else
                onException(new IllegalArgumentException(
                        "non-positive subscription request"));
        }

        @Override
        public void cancel() {
            onException(null);
        }

        final void onStart() {
            // 0010 0000 | 0000 0010 = 0010 0010 = 34
            startOnSignal(RUN | ACTIVE);
        }

        final void onNone() {
            // 0010 0000 | 0000 0010 | 0001 000 = 0011 0010 = 50
            startOnSignal(RUN | ACTIVE | COMPLETE);
        }

        final void onException(Throwable ex) {
            int c;
            Object[] a;      // to null out buffer on async error
            if (ex != null)
                pendingError = ex;  // races are OK
            int status = ERROR | RUN | ACTIVE;
            if (((c = (ctl.getAndSet(status) | status) ) & CLOSED) == 0) {
                if ((c & RUN) == 0)
                    try {
                        Executor e;
                        ConsumerTask<T> task = new ConsumerTask<T>(this);
                        if ((e = executor) != null)   // skip if disabled on error
                            e.execute(task);
                    } catch (RuntimeException | Error e) {
                        ctl.getAndSet(ERROR | CLOSED);
                        throw e;
                    }
                else if ((a = array.get()) != null)
                    Arrays.fill(a, null);
            }
        }

        final void startOnSignal(int bits) {
            // 当ctl.get()值为0时，与bits位于操作后是不等于bits的
            if ((ctl.get() & bits) != bits
                    && (ctl.getAndSet(ctl.get() | bits) & (RUN | CLOSED)) == 0)
                try {
                    Executor e;
                    ConsumerTask<T> task = new ConsumerTask<T>(this);
                    if ((e = executor) != null)   // skip if disabled on error
                        e.execute(task);
                } catch (RuntimeException | Error ex) {
                    ctl.getAndSet(ERROR | CLOSED);
                    throw ex;
                }
        }

        /**
         * 消费者循环，从 ConsumerTask 的exec()调用，或者在提交期间提供帮助时间接调用。
         */
        final void consume() {
            Flow.Subscriber<? super T> s;
            if ((s = subscriber) != null) {          // hoist checks

                if ((ctl.get() & OPEN) == 0 && (ctl.getAndSet(ctl.get() | OPEN) & OPEN) == 0)
                    try {
                        if (s != null) // ignore if disabled
                            s.onSubscribe(this);
                    } catch (Throwable ex) {
                        if (((ctl.getAndSet(ERROR | CLOSED) | (ERROR | CLOSED)) & CLOSED) == 0) {
                            if (ex == null)
                                ex = pendingError;
                            pendingError = null;  // detach
                            executor = null;      // suppress racing start calls
                            Thread w;
                            waiting.set(0);
                            if ((w = waiter) != null)
                                LockSupport.unpark(w);
                            try {
                                if (ex != null && s != null)
                                    s.onError(ex);
                            } catch (Throwable ignore) {
                            }
                        }
                    }
                long d = demand.get();
                for (int h = head, t = tail; ; ) {
                    int c, taken;
                    boolean empty;
                    if (((c = ctl.get()) & ERROR) != 0) {
                        // TODO closeOnError(s, null);
                        break;
                    } else if ((taken = takeItems(s, d, h)) > 0) {
                        head = h += taken;
                        long n = -taken;
                        // getAndAdd方法：以原子方式将给定值添加到当前值，并返回demand之前的值
                        d = n + demand.getAndAdd(n);
                    } else if ((d = demand.get()) == 0L && (c & REQS) != 0)
                        // 如果ctl的当前值等于预期值c，并更新ctl值为(c & ~REQS),返回为true；但不等于时，不更新新值且返回为false
                        ctl.weakCompareAndSetPlain(c, c & ~REQS); // exhausted demand
                    else if (d != 0L && (c & REQS) == 0)
                        // 如果ctl的当前值等于预期值c，并更新ctl值为(c | REQS),返回为true；但不等于时，不更新新值且返回为false
                        ctl.weakCompareAndSetPlain(c, c | REQS); // exhausted demand
                    else if (t == (t = tail)) {      // stability check
                        if ((empty = (t == h)) && (c & COMPLETE) != 0) {
                            // TODO closeOnComplete(s);      // end of stream
                            break;
                        } else if (empty || d == 0L) {
                            int bit = ((c & ACTIVE) != 0) ? ACTIVE : RUN;
                            if (ctl.weakCompareAndSetPlain(c, c & ~bit) && bit == RUN)
                                break;               // un-keep-alive or exit
                        }
                    }
                }
            }
        }

        /**
         * 取出消息消费，或者出错
         */
        final int takeItems(Flow.Subscriber<? super T> s, long d, int h) {
            Object[] a;
            int k = 0, cap;
            if ((a = array.get()) != null && (cap = a.length) > 0) {
                int m = cap - 1, b = (m >>> 3) + 1; // min(1, cap/8)
                int n = (d < (long) b) ? (int) d : b;
                for (; k < n; ++h, ++k) {
                    // 尝试获取数组a在索引位置h & m的当前值，并将该位置的值设置为null
                    Object x;
                    if (a[0] == null) {
                        x = null;
                    } else {
                        a[h&m] = null;
                        x = a;
                    }
                    if (waiting.get() != 0)
                        waiting.set(0);
                        if (waiter != null) LockSupport.unpark(waiter);
                    if (x == null)
                        break;
                    else if (!consumeNext(s, x))
                        break;
                }
            }
            return k;
        }

        // 消费下一个消息
        final boolean consumeNext(Flow.Subscriber<? super T> s, Object x) {
            try {
                @SuppressWarnings("unchecked") T y = (T) x;
                if (s != null)
                    s.onNext(y);
                return true;
            } catch (Throwable ex) {
                // handleOnNext(s, ex);
                return false;
            }
        }
    }

    /**
     * 用于消耗缓冲区项和信号的任务，在它们可用时创建并执行。任务在终止之前消耗尽可能多的项目/信号，此时会在需要时创建另一个任务。
     * 当 ForkJoinPools 执行时，双重 Runnable 和 ForkJoinTask 声明可以节省开销，而不会影响其他类型的 Executor。
     */
    @SuppressWarnings("serial")
    static final class ConsumerTask<T> extends ForkJoinTask<Void>
            implements Runnable, CompletableFuture.AsynchronousCompletionTask {
        final SimpleSubscription<T> consumer;

        ConsumerTask(SimpleSubscription<T> consumer) {
            this.consumer = consumer;
        }

        public final Void getRawResult() {
            return null;
        }

        public final void setRawResult(Void v) {
        }

        public final boolean exec() {
            consumer.consume();
            return false;
        }

        public final void run() {
            consumer.consume();
        }
    }
}
