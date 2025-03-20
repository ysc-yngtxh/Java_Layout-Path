package P16_JUC并发.JUC并发Ⅳ_集合类不安全;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteSet线程安全 {
    /*
     * 并发下Set是不安全的，如何解决呢？
     *    public static void main(String[] args) {
     *        Set<String> list = new HashSet<>();
     *        for (int i = 0; i < 10; i++) {
     *            new Thread(() -> {
     *                list.add(UUID.randomUUID().toString().substring(0,5));
     *                System.out.println(list);
     *            }, String.valueOf(i)).start();
     *        }
     *    }
     *    //会出现java.util.ConcurrentModificationException异常，并发修改异常
     *
     *  方案一：Collections.SynchronizedList(new HashSet())
     *  方案二：Set<String> list = new CopyOnWriteArraySet<>()
     *         和 CopyOnWriteArraySet() 集合类似原理
     */
    public static void main(String[] args) {
        Set<String> list = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add( UUID.randomUUID().toString().substring(0, 5) );
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
