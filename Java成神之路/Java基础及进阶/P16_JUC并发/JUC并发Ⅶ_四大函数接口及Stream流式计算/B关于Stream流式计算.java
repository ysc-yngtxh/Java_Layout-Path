package P16_JUC并发.JUC并发Ⅶ_四大函数接口及Stream流式计算;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class B关于Stream流式计算 {
    /**
     * 题目要求：一分钟内完成此题，只能用一行代码实现！
     *    现在有五个用户！筛选：
     *       1、ID 必须是偶数
     *       2、年龄必须大于23岁
     *       3、用户名转为大写字母
     *       4、用户名字倒着排序
     *       5、最多输出三个用户
     *       6、用户不能重复
     */
    public static void main(String[] args) {
        User user1 = new User(1, "a1", 21);
        User user2 = new User(2, "b1", 22);
        User user3 = new User(3, "c1", 23);
        User user4 = new User(4, "d1", 24);
        User user5 = new User(5, "e1", 25);
        User user6 = new User(6, "e1", 25);
        // Arrays.asList得到的List的长度是不可改变的，当你向这个List添加或删除一个元素时（例如 list.add("d");）程序就会抛出异常
        // 如果你的List只是用来遍历，就用Arrays.asList()。
        // 如果你的List还要添加或删除元素，还是乖乖地new一个java.util.ArrayList，然后一个一个的添加元素。
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6);


        /*计算交给Stream流,链式编程（主要针对集合编程）
             filter(过滤):将数据流中满足过滤条件的数据筛选出来
             map(映射)：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
             sorted(Comparator com):定制排序，自定义Comparator排序器
             limit(n)：获取n个元素
             skip(n)：跳过前n元素
             distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素
             peek：如同于map，能得到流中的每一个元素。但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值。

             Count(计数)：统计到Count函数时的数据流中数据个数
             max：返回流中元素最大值
             min：返回流中元素最小值
        */
        list.stream()
                .filter(user -> user.getId()%2 == 0)
                .filter(user -> {return user.getAge() > 23;})
                .map(user -> {return user.getName().toUpperCase();})
                // .sorted((uu1,uu2) -> {return uu2.compareTo(uu1);}) // E1 E1 D1
                .sorted(Comparable::compareTo)
                .distinct() // E1 D1
                .skip(1)    // D1
                .limit(3)
                .forEach(System.out::println);



        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

        // Stream<String> stream = list.stream(); // 获取一个顺序流
        Integer v2 = lists.stream().reduce( 0,
                // 这个 lambda 表达式定义了如何将流中的元素累积起来。它接收两个参数 x1 和 x2，分别代表当前的累积值和流中的下一个元素。
                (x1, x2) -> {
                    System.out.println("stream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                // 这个 lambda 表达式通常用于并行流，当流被分割成多个子流并行处理时，每个子流都会有一个自己的累加器函数。
                // 然而，由于这段代码中的流不是并行流，组合器函数实际上不会被执行。
                (x1, x2) -> {
                    System.out.println("stream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        // (0-1, 0-2, 0-3, 0-4, 0-5, 0-6, 0-7, 0-8, 0-9, ......)
        System.out.println("获取一个顺序流 " + v2);

        // Stream<String> parallelStream = list.parallelStream(); // 获取一个并行流
        // 经过测试，当元素个数小于24时，并行时线程数等于元素个数，当大于等于24时，并行时线程数为16
        Integer v3 = lists.parallelStream().reduce(0,
                (x1, x2) -> {
                    System.out.println("parallelStream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("parallelStream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        System.out.println("获取一个并行流 " + v3);

        /**
         * 使用Arrays 中的 stream() 方法，将数组转成流
         *    Integer[] nums = new Integer[10];
         *    Stream<Integer> stream = Arrays.stream(nums);
         */
    }
}
class User{
    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}