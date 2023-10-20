package P16_JUC并发.JUC并发Ⅱ_集合类不安全;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayList线程安全 {

    /*并发下ArrayList是不安全的，如何解决呢？
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        //会出现java.util.ConcurrentModificationException异常，并发修改异常

     方案一：Collections.SynchronizedList(new ArrayList())
     方案二：使用Vector集合,我们可以查看源码发现Vector是有Synchronized关键字的
     方案三：List<String> list = new CopyOnWriteArrayList()
            CopyOnWrite容器即写时复制的容器。通俗的理解是当我们往一个容器添加元素的时候，不直接往当前容器添加，
            而是先将当前容器进行Copy，复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，
            再将原容器的引用指向新的容器。这样做的好处是我们可以对CopyOnWrite容器进行并发的读，而不需要加锁，
            因为当前容器不会添加任何元素(但是在写入的时候还是会加一个Lock锁)。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
    }*/

    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add( UUID.randomUUID().toString().substring(0, 5) );
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
