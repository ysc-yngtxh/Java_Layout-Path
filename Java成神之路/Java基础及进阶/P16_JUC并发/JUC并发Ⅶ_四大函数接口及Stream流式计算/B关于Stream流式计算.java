package P16_JUC并发.JUC并发Ⅶ_四大函数接口及Stream流式计算;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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


        /* 计算交给Stream流，链式编程（主要针对集合编程）
         *    filter(过滤):将数据流中满足过滤条件的数据筛选出来
         *    map(映射)：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
         *    sorted(Comparator com)：定制排序，自定义Comparator排序器
         *    limit(n)：获取n个元素
         *    skip(n)：跳过前n元素
         *    distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素
         *    peek：如同于map，能得到流中的每一个元素。但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值。
         *
         *    Count(计数)：统计到Count函数时的数据流中数据个数
         *    max：返回流中元素最大值
         *    min：返回流中元素最小值
         */
        list.stream()
                .filter(user -> user.getId()%2 == 0)
                .filter(user -> {return user.getAge() > 23;})
                .map(user -> {return user.getName().toUpperCase();})
                // .sorted((uu1,uu2) -> {return uu2.compareTo(uu1);}) // E1 E1 D1
                .sorted(Comparable::compareTo)
                .distinct()    // E1 D1
                .skip(1)    // D1
                .limit(3)
                .forEach(System.out::println);

        User user = list.stream().max(Comparator.comparing(User::getAge)).get();

        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

        // TODO reduce() 方法用于将流中的元素组合起来生成一个单一的结果。它通常用于求和、求积、连接字符串或查找极值等操作。
        //      list.stream();  获取一个顺序流，reduce() 通常只使用两个参数的重载方法，三个参数的重载方法用于并行流
        Integer v2 = lists.stream().reduce( 0,
                // 这个 lambda 表达式定义了如何将流中的元素累积起来。它接收两个参数 x1 和 x2，分别代表当前的累积值和流中的下一个元素。
                (x1, x2) -> {
                    System.out.println("stream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                });
        System.out.println("求和:  " + v2); // (0-1, 0-2, 0-3, 0-4, 0-5, 0-6, 0-7, 0-8, 0-9, ......)

        // 使用 reduce 求最大值
        int max = lists.stream().reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
        System.out.println("reduce 求最大值: " + max);

        // 使用 reduce 连接字符串
        List<String> words = Arrays.asList("Java", "C++", "C#", "Python", "Go");
        String concatString = words.stream().reduce("", (a, b) -> a + " " + b);
        System.out.println("reduce 连接字符串: " + concatString);

        // TODO list.parallelStream();  获取一个并行流
        // 经过测试，当元素个数小于24时，并行时线程数等于元素个数，当大于等于24时，并行时线程数为16
        List<String> letter = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                                            "A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1", "I1", "J1", "K1", "L1", "M1", "N1", "O1", "P1", "Q1", "R1", "S1", "T1", "U1", "V1", "W1", "X1", "Y1", "Z1",
                                            "A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2", "I2", "J2", "K2", "L2", "M2", "N2", "O2", "P2", "Q2", "R2", "S2", "T2", "U2", "V2", "W2", "X2", "Y2", "Z2",
                                            "A3", "B3", "C3", "D3", "E3", "F3", "G3", "H3", "I3", "J3", "K3", "L3", "M3", "N3", "O3", "P3", "Q3", "R3", "S3", "T3", "U3", "V3", "W3", "X3", "Y3", "Z3",
                                            "A4", "B4", "C4", "D4", "E4", "F4", "G4", "H4", "I4", "J4", "K4", "L4", "M4", "N4", "O4", "P4", "Q4", "R4", "S4", "T4", "U4", "V4", "W4", "X4", "Y4", "Z4",
                                            "A5", "B5", "C5", "D5", "E5", "F5", "G5", "H5", "I5", "J5", "K5", "L5", "M5", "N5", "O5", "P5", "Q5", "R5", "S5", "T5", "U5", "V5", "W5", "X5", "Y5", "Z5");
        String v3 = letter.parallelStream().reduce("@",
                // 累加器函数，用于处理子流中的元素，x1 代表当前的累积值，x2 代表流中的下一个元素
                (x1, x2) -> {
                    System.out.println("parallelStream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 + x2;
                },
                // 追加器函数，用于合并子流的结果，x1 代表第一个部分的结果，x2 代表第二个部分的结果
                (x1, x2) -> {
                    System.out.println("parallelStream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 + "-" + x2;
                });
        System.out.println("获取一个并行流 " + v3); // @abcd-@efghi-@jklmn-@opqrs-@tuvwx-@yzA1B1C1......
        /* 简述：在并行流中开启多线程，每个线程都会使用 "@"（identity）初始值，并按照 累加器 处理一部分数据，
         *      最后将所有线程的结果按照 追加器 合并。
         */

        /**
         * 使用Arrays 中的 stream() 方法，将数组转成流
         *    Integer[] nums = new Integer[10];
         *    Stream<Integer> stream = Arrays.stream(nums);
         */
    }
}

class User {

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
