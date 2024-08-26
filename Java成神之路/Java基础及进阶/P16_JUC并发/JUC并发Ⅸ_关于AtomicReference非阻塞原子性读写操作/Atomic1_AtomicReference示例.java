package P16_JUC并发.JUC并发Ⅸ_关于AtomicReference非阻塞原子性读写操作;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-21 07:06
 * @apiNote TODO 高并发环境下的性能优势 LongAdder，AtomicLong
 */
public class Atomic1_AtomicReference示例 {

    /**
     * AtomicReference类提供了对象引用的非阻塞原子性读写操作，并且提供了其他一些高级的用法
     * <p>
     *     对象的引用其实是一个4字节的数字，代表着在JVM堆内存中的引用地址，对一个4字节数字的读取操作和写入操作本身就是原子性的操作，
     * 通常情况下，我们对对象引用的操作一般都是获取该引用或者重新赋值（写入操作），我们也没有办法对对象引用的4字节数字进行加减乘除运算，
     * 那么为什么JDK要提供AtomicReference类用于支持引用类型的原子性操作呢？
     */

    // volatile关键字修饰，每次对 DebitCard对象引用 的写入操作都会被其他线程看到。创建初始DebitCard，账号金额为0元
    static volatile DebitCard debitCard = new DebitCard("ZhangSan", 0);

    // 定义AtomicReference并且初始值为DebitCard("ZhangSan", 0)
    private static AtomicReference<DebitCard> debitCardRef = new AtomicReference<>(new DebitCard("ZhangSan", 0));

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread("T-" + i){
                @Override
                public void run() {
                    final DebitCard dc = debitCard;
                    // 基于全局 DebitCard 的金额增加10元并且产生一个新的DebitCard
                    DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                    // 输出全新的DebitCard
                    System.out.println("只使用 Volatile 打印的金额" + newDC);
                    // 修改全局DebitCard对象的引用
                    debitCard = newDC;
                    try {
                        TimeUnit.MILLISECONDS.sleep(current().nextInt(20));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        // 根据执行结果可以发现：总是有重复的打印金额
        // 1、原因就在于我们使用的 volatile 关键字是不保证原子性的，以上程序 debitCard = newDC; 执行就不是原子性的
        // 2、该行程序包含以下操作：
        //       (1)、首先从主内存中读取到共享变量 debitCard 最新值，并将最新值复制到自己的工作内存中;
        //       (2)、然后在工作内存中将 newDC 赋值给 debitCard;
        //       (3)、最后将工作内存里将赋值后的结果 刷新到主内存的共享变量 debitCard 里
        // 3、设：线程1、2同时执行，线程1执行完第(2)步后被挂起、线程2执行完并且把debitCard的新值(金额值为10)刷回主存，那么主存中debitCard的金额值为10
        //       随后线程1的开始执行第(3)步，这时由于 volatile 的可见性，线程1的工作内存读取到主存的 debitCard 最新金额值为10，
        //       但是这个 debitCard 最新值影响不了线程1中执行过 dc.getAmount()+10 过的  newDC 值。且 newDC 的金额值为10。
        //       所以线程1工作内存中 debitCard 的新值(金额值为10)刷新到主存。即 dc.getAmount() + 10 执行了2次，但2次都是从0变为10，故有重复的打印金额


        TimeUnit.SECONDS.sleep(3);
        System.out.println("=========================================================================");


        /**
         * synchronized是一种阻塞式的解决方案，同一时刻只能有一个线程真正在工作，其他线程都将陷入阻塞。
         * 因此这并不是一种效率很高的解决方案。这个时候就可以利用 AtomicReference 的非阻塞原子性解决方案提供更加高效的方式了
         */
        for (int i = 0; i < 10; i++) {
            new Thread("T-" + i) {
                @Override
                public void run() {
                    // 获取AtomicReference的当前值
                    final DebitCard dc = debitCardRef.get();
                    // 基于AtomicReference的当前值创建一个新的DebitCard
                    DebitCard newDC = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                    // 基于CAS算法更新AtomicReference的当前值
                    if(debitCardRef.compareAndSet(dc, newDC)){
                        // 更新成功
                        System.out.println("使用 AtomicReference 打印的金额" + newDC);
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(current().nextInt(20));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}