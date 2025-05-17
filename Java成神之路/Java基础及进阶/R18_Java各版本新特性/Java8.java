package R18_Java各版本新特性;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-22 08:00
 * @apiNote TODO
 */
/*
 * Java8新特性
 *     1、现在的方法区在Java8之前是属于堆中的一部分叫作永久区
 *     2、HashMap的数据结构在Java8之后的某一种情况下会由数组加链表结构变为数组加红黑树结构
 *     3、增加了Lambda表达式
 *          // 原来的匿名内部类
 *          public void Test() {
 *             Comparator<Integer> com = new Comparator<Integer>(){
 *                 @Override
 *                 public int compare(Integer o1, Integer o2){
 *                     return Integer.compare(o1, o2);
 *                 }
 *          };
 *
 *          // Lambda表达式
 *          public void Test() {
 *             Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
 *          }
 *     4、强大的Stream API
 *     5、最大化减少空指针异常 Optional
 *
 * 一、Lambda表达式的基础语法：java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或Lambda操作符
 *        箭头操作符将Lambda表达式拆分成两部分：
 *            左侧：Lambda表达式的参数列表，不用写数据量类型，可以省。因为JVM可以进行上下文推断出来
 *            右侧：Lambda表达式中所需执行的功能，即Lambda体
 *        Lambda表达式需要"函数式接口"的支持，函数式接口指的是接口中只有一个抽象方法的接口，可以使用注解@FunctionalInterface修饰，检查是否是函数式接口。
 *
 * 二、Java内置的四大核心函数式接口
 *        Consumer<T>：消费者接口
 *            void accept(T t);
 *        Supplier<T>：供给型接口           Math.random() * 100      表示产生0-100的随机数
 *             T get();                   Math.random() * (100+1)  表示产生1-100的随机数
 *        Function<T,R>：函数型接口
 *             R apply(T t);
 *        Predicate<T>：断言型接口
 *             boolean Test(T t);
 *
 * 三、方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用"方法引用"
 *             (可以理解为方法引用是是 Lambda表达式得另一种表现形式)
 *     主要有三种语法格式：
 *        1、对象::实例方法名
 *           public void Test() {
 *                Consumer<String> con = (x) -> System.out.println(x);
 *                PrintStream ps = System.out;
 *                Consumer<String> con1 = ps::println;
 *           }
 *
 *        2、类::静态方法名
 *           public void Test() {
 *               Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
 *               Comparator<Integer> com1 = Integer::compare;
 *           }
 *
 *        3、类::实例方法名
 *           public void Test() {
 *               BiPredicate<String,String> bp = (x,y) -> x.equals(y);
 *               BiPredicate<String,String> bp2 = String::equals;
 *           }
 * 四、Stream API
 *     public void Test() {
 *         // 1、可以通过Collection系列集合提供的stream()或parallelStream()
 *         List<String> list = new ArrayList<>();
 *         Stream<String> stream = list.stream();
 *
 *         // 2、通过Arrays中的静态方法stream()获取数组流
 *         User[] user = new User[10];
 *         Stream<User> stream2 = Arrays.stream(user);
 *
 *         // 3、通过Stream类中的静态方法of()
 *         Stream<String> stream3 = Stream.of("aa", "bb", "cc");
 *
 *         // 4、创建无限流
 *         Stream<Integer> stream4 = Stream.iterate(0, (x) -> x+2);
 *         stream4.limit(10).forEach(System.out::println);
 *
 *         // 生成
 *         Stream.generate(() -> Math.random())
 *                 .limit(5)
 *                 .forEach(System.out::println);
 *     }
 */
public class Java8 {}
