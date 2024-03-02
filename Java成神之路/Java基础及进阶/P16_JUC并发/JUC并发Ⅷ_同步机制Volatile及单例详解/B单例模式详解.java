package P16_JUC并发.JUC并发Ⅷ_同步机制Volatile及单例详解;

/**
 * 什么是单例模式？
 * 保证整个系统中一个类只有一个对象的实例，实现这种功能的方式就叫单例模式。
 *
 * 为什么要用单例模式?
 *     1、单例模式节省公共资源
 *         比如：大家都要喝水，但是没必要每人家里都打一口井是吧，通常的做法是整个村里打一个井就够了，大家都从这个井里面打水喝。
 *         对应到我们计算机里面，像日志管理、打印机、数据库连接池、应用配置。
 *
 *     2、单例模式方便控制
 *         就像日志管理，如果多个人同时来写日志，你一笔我一笔那整个日志文件都乱七八糟，如果想要控制日志的正确性，
 *         那么必须要对关键的代码进行上锁，只能一个一个按照顺序来写，而单例模式只有一个人来向日志里写入信息方便控制，避免了这种多人干扰的问题出现。
 *
 * 实现单例模式的思路
 *     1.构造私有:
 *         如果要保证一个类不能多次被实例化，那么我肯定要阻止对象 被new 出来，所以需要把类的所有构造方法私有化。
 *     2.以静态方法返回实例。
 *         因为外界就不能通过new来获得对象，所以我们要通过提供类的方法来让外界获取对象实例。
 *     3.确保对象实例只有一个。
 *         只对类进行一次实例化，以后都直接获取第一次实例化的对象。
 */
@SuppressWarnings({"NonAsciiCharacters", "InstantiationOfUtilityClass"})
public class B单例模式详解 {
    /**
     * 饿汉式单例
     *     类一旦加载就创建一个单例，保证在调用 getInstance 方法之前单例已经存在了
     *     饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的，可以直接用于多线程而不会出现问题
     *     但是缺点就是如果这个类需要的提前就加载好，比较浪费内存空间
     */
    private B单例模式详解(){  

    }

    private static B单例模式详解 B = new B单例模式详解();

    public static B单例模式详解 getInstance(){
        return B;
    }
}
    

/**
 * 线程不安全的懒汉式单例
 *    顾名思义就是实例在用到的时候才去创建，"比较懒"，用的时候才去检查有没有实例，如果有则返回，没有则新建。
 *    有线程安全和线程不安全两种写法，区别就是synchronized关键字。
 *
 * 思考一下：  为什么会线程不安全？
 *           比如有两个线程a,b。都在其run()方法中调用了懒汉式单例的静态方法getInstance()
 *           线程a会发现 lazyMan == null,就会去实例化对象，得到LazyMan@5643x
 *           线程b在线程a实例化对象时，也发现了lazyMan  == null，也去实例化对象，得到LazyMan@4564x
 */
@SuppressWarnings("InstantiationOfUtilityClass")
class LazyMan{
    private LazyMan(){

    }

    private static LazyMan lazyMan;

    public static LazyMan getInstance(){
        if (null == lazyMan) {
            lazyMan = new LazyMan();
        }
        return lazyMan;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println( Thread.currentThread().getName() + "--" + LazyMan.getInstance() );
            }, "a").start();
        }

        new Thread(() -> {
            System.out.println( Thread.currentThread().getName() + "--" + LazyMan.getInstance() );
        }, "b").start();
    }
}


/**
 * 线程安全的懒汉式单例
 */
class Singleton{
    private String str;
    public String getStr() {
        return str;
    }
    private Singleton(){
        str = "hello";
    }

    private static volatile Singleton singleton; // 第二层锁，volatile关键字禁止指令重排
    public static Singleton getInstance(){
        if(singleton == null){              // 第一层检查，检查是否有引用指向对象，高并发情况下会有多个线程同时进入
            synchronized (Singleton.class){ //第一层锁，保证只有一个线程进入
                // 双重检查，防止多个线程同时进入第一层检查(因单例模式只允许存在一个对象，故在创建对象之前无引用指向对象，所有线程均可进入第一层检查)
                // 当某一线程获得锁创建一个Singleton对象时,即已有引用指向对象，singleton不为空，从而保证只会创建一个对象
                if(singleton == null){      // 第二层检查。假设没有第二层检查，那么第一个线程创建完对象释放锁后，后面进入对象也会创建对象，会产生多个对象
                    // volatile关键字作用为禁止指令重排，保证返回Singleton对象一定在创建对象后
                    singleton = new Singleton();
                    // singleton = new Singleton 语句为非原子性，实际上会执行以下内容：
                    // (1)在堆上开辟空间；(2)属性初始化；(3)引用指向对象
                    // 假设以上三个步骤为三条单独指令，因指令重排可能会导致执行顺序为 1 -> 3 -> 2 (正常为 1 -> 2 -> 3),
                    // 当单例模式中存在普通变量需要在构造方法中进行初始化操作时，并发情况下线程1执行singleton = new Singleton()语句时先(1)再(3)，
                    // 此时由于系统调度 线程2 的原因导致 线程1 没来得及执行步骤(2)，但此时已有引用指向对象也就是说 singleton 指向了一段地址 @****
                    // 故线程2在第一层检查因不满足条件直接返回singleton，此时singleton为 @**** (即此时的str没有初始化且值为 null )
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println( Singleton.getInstance() );
            }).start();
        }

        System.out.println( Singleton.getInstance().getStr() );
    }
}
