package P16_JUC并发.JUC并发Ⅸ_关于AtomicReference非阻塞原子性读写操作;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-22 19:05
 * @apiNote TODO
 */
public class Atomic3_AtomicReference类API {
    /**
     * AtomicReference 类是Java在 java.util.concurrent.atomic 包下Atomic相关的原子操作类
     * atomic 包下原子更新基本数据类型还包括 AtomicInteger、AtomicLong、AtomicBoolean 三个类
     * 这里我们只做分析 AtomicReference类的 Api；其他的三个类有兴趣自己去了解～
     */
    public static void main(String[] args) {

        AtomicReference<DebitCard> reference = new AtomicReference<>();

        // set(V newValue)：设置 AtomicReference 最新的对象引用值，该新值的更新对其他线程立即可见。
        reference.set( new DebitCard("ysc", 10) );

        // get()：获取 AtomicReference 的当前对象引用值。
        DebitCard debitCard = reference.get();
        System.out.println( "get()方法获取当前引用值：" + debitCard );

        // getAndSet(V newValue)：原子性地更新AtomicReference内部的value值，并且返回AtomicReference的旧值。
        System.out.println( "getAndSet()方法更新引用值并返回旧值：" + reference.getAndSet(new DebitCard("ysc", 20)) );

        // compareAndSet(V expect, V update)：原子性地更新AtomicReference内部的value值，该方法会返回一个boolean的结果
        // 其中 expect 代表当前AtomicReference的value值，update 则是需要设置的新引用值
        // 当 expect 和AtomicReference的当前值不相等时，修改会失败，返回值为false；若修改成功则会返回true
        boolean compared = reference.compareAndSet(debitCard, new DebitCard("cym", 10));
        System.out.println( "compareAndSet()方法修改情况：" + compared );


        System.out.println("===================================================================");


        AtomicReference<String> atomicTypeString = new AtomicReference<>("画江湖之不良人");

        // getAndUpdate(UnaryOperator<V> updateFunction)：
        // 原子性地更新value值，并且返回AtomicReference的旧值，该方法需要传入一个Function接口。
        String andUpdate = atomicTypeString.getAndUpdate(value -> value.substring(0, 3));
        System.out.println( "getAndUpdate()方法更新返回的旧值：" + andUpdate );
        System.out.println( "getAndUpdate()方法更新后的新值：" + atomicTypeString.get() );

        // updateAndGet(UnaryOperator<V> updateFunction)：
        // 原子性地更新value值，并且返回AtomicReference更新后的新值，该方法需要传入一个Function接口。
        String updateAndGet = atomicTypeString.updateAndGet(value -> value + "李星云");
        System.out.println( "updateAndGet()方法更新返回的新值：" + updateAndGet );
    }
}