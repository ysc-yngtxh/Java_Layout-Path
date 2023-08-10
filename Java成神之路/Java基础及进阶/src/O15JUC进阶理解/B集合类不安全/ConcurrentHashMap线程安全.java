package O15JUC进阶理解.B集合类不安全;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMap线程安全 {
    /*并发下HashMap是不安全的,为什么不安全呢？
          因为在并发状况，去进行写的操作，很有可能会被插队。就可能导致读的结果与预期不一样，就会出现线程不安全
          假设两个线程A、B都在进行put操作，并且hash函数计算出的插入下标是相同的，当线程A执行完第六行代码后由于时间片耗尽导致被挂起，
          而线程B得到时间片后在该下标处插入了元素，完成了正常的插入，然后线程A获得时间片，由于之前已经进行了hash碰撞的判断，
          所以此时不会再进行判断，而是直接进行插入，这就导致了线程B插入的数据被线程A覆盖了，从而线程不安全。

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        for (int i = 1; i < 10; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
        //会出现java.util.ConcurrentModificationException异常，并发修改异常

        方案一：使用集合Properties,他是继承于Hashtable的，都是线程安全的集合
        方案二：Map<Object,String> map = Collections.synchronizedMap(new HashMap<>())
        方案二：Map<String,String> map = new ConcurrentHashMap<>();
               ConcurrentHashMap<>()集合类似原理
    }*/

    // public static void main(String[] args) {
    //     Map<Object,String> map = new ConcurrentHashMap<>();
    //     for (int i = 0; i < 10; i++) {
    //         new Thread(()->{
    //             map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
    //             System.out.println(Thread.currentThread().getName()+"---"+map);
    //         },String.valueOf(i)).start();
    //     }
    // }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        for (int i = 1; i < 10; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(Thread.currentThread().getName()+"---"+map);
            },String.valueOf(i)).start();
        }
    }

}
