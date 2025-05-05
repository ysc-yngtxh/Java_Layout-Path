package com.example.补充Stream方法及增强Stream依赖包Api;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.vo.ModelView;
import com.example.vo.Models;
import com.example.vo.User;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/11/28 15:54
 */
public class StreamApi {
    public static final Logger log = LoggerFactory.getLogger(StreamApi.class);

    static {
        // 默认情况下，SLF4J 的日志级别是 INFO。强制设置日志级别为 DEBUG（适用于 Logback）
        ((ch.qos.logback.classic.Logger) log).setLevel(ch.qos.logback.classic.Level.DEBUG);
    }

    List<User> users = Arrays.asList(
            new User(1L, null, null, 0, null, new Models(new ModelView("WUHAN"), null), null),
            new User(2L, "YouShiCheng", "google@163.com", 25, null, new Models(new ModelView("shenzhen"), null), null)
    );

    @Test
    public void test1() {
        // TODO 1、anyMatch(), allMatch(), noneMatch()
        //         anyMatch：判断的条件里，任意一个元素成功，返回true
        //         allMatch：判断条件里的元素，所有的都是，返回true
        //         noneMatch：与allMatch相反，判断条件里的元素，所有的都不是，返回true
        List<String> list = Arrays.asList("hello", "si");
        boolean isValid = list.stream().anyMatch(element -> element.contains("h"));     // true
        boolean isValidOne = list.stream().allMatch(element -> element.contains("h"));  // false
        boolean isValidTwo = list.stream().noneMatch(element -> element.contains("h")); // false
        System.out.println(isValid + "\n" + isValidOne + "\n" + isValidTwo);

        /** TODO 2、归集 Reduction
         *  Stream API中使用reduce()方法可以根据指定的方法将一系列元素归集为某个值，
         *  该方法接收两个参数：第一个是初始值，第二个是累加器函数。
         *  假设您有一个整数列表，并且想要在某个初始值（这里使用23）基础上计算所有元素的总和，运行得到结果为26（23+1+1+1）
         */
        List<Integer> integers = Arrays.asList(1, 1, 1);
        Integer reduced1 = integers.stream().reduce(23, (a, b) -> a + b);
        Integer reduced2 = integers.stream().reduce(23, Integer::sum);  // 同上效果一样
    }

    @Test
    public void test2() {
        // TODO 3、分组 groupingBy
        // 第一个例子中，为每个组创建了Person集合；
        Map<Models, List<User>> peopleByGender = users.stream()
                .collect(Collectors.groupingBy(User::getModels, Collectors.toList()));
        log.info(peopleByGender.toString());
        // 第二个例子中，根据id分组，并排序(第一个参数：作为返回Map的key值  第二个参数：分组最后用什么容器保存返回  第三个参数：按照第一个参数分类后，对应的分类的结果如何收集)
        Map<Long, List<User>> peopleByGenderTree = users.stream()
                .collect(Collectors.groupingBy(User::getId, LinkedHashMap::new, Collectors.toList()));
        log.info(peopleByGenderTree.toString());
        // 第三个例子中，通过Collectors.mapping()是提取每个用户的姓名，并创建姓名集合；
        Map<Models, List<String>> nameByGender = users.stream().collect(
                Collectors.groupingBy(User::getModels, Collectors.mapping(User::getName, Collectors.toList())));
        log.info(nameByGender.toString());
        // 第四个例子中，提取并计算每组的平均年龄。(这里求和的返回值类型只能是Double)
        Map<Models, Double> averageAgeByGender = users.stream()
                .collect(Collectors.groupingBy(User::getModels, Collectors.averagingInt(User::getAge)));
        log.info(averageAgeByGender.toString());
    }

    @Test
    public void test3() {
        // TODO 3、分组 join
        List<String> list = Arrays.asList("hello", "si", "world");
        // 第一个例子中，直接将集合数据进行拼接，中间没有分隔符
        String joinStr1 = list.stream().collect(Collectors.joining());
        log.info(joinStr1);
        // 第二个例子中，分隔符为 " - "
        String joinStr2 = list.stream().collect(Collectors.joining(" - "));
        log.info(joinStr2);
        // 第三个例子中，分隔符为 " - "，前缀为 "@" ， 后缀为 "$"
        String joinStr3 = list.stream().collect(Collectors.joining(" - ", "@", "$"));
        log.info(joinStr3);
    }

    @Test
    public void test4() {
        // TODO 4、合并两个流的话，可以使用静态方法Stream.concat()
        Stream<Integer> stream1 = Stream.of(1, 3, 5);
        Stream<Integer> stream2 = Stream.of(2, 4, 6);
        Stream<Integer> stream3 = Stream.of(7, 8, 9);
        Stream<Integer> resultingStream1 = Stream.concat(stream1, stream2);
        // Stream<Integer> resultingStream2 = Stream.concat(Stream.concat(stream1, stream2), stream3);
        // 当合并的流比较多的时候，以上写法就不优雅了
        /**
         * ①、首先创建包含四个流的新流，其结果为Stream<Stream<Integer>>。
         * ②、然后使用flatMap()和恒定式将其转换为一个Stream<Integer>。
         * */
        Stream<Integer> resultingStream = Stream.of(stream1, stream2, stream3).flatMap(i -> i);

        // TODO 5、创建一个IntStream并使用range()方法在一个范围内添加流元素。这将在范围内以1的增量步长返回顺序的有序
        String[] names = {"Afrim", "Bashkim", "Besim", "Lulzim", "Durim", "Shpetim"};
        // [1、Afrim, 2、Bashkim, 3、Besim, 4、Lulzim, 5、Durim, 6、Shpetim]
        IntStream.range(1, names.length)
                //.filter(i -> i % 2 == 0)
                .mapToObj(i -> i + "、" + names[i])
                .collect(Collectors.toList())
                .forEach(System.out::println);
        // IntStream和Stream都是继承了BaseStream，是同级关系。一般使用IntStream前都需要通过mapToObj()方法转为Stream流
        int[] item = {1, 2, 3, 4, 5};
        Arrays.stream(item)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // TODO 6、求和
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);
        // 正常Stream的求和
        Integer sum1 = items.stream()
                .collect(Collectors.summingInt(Integer::intValue));
        // 使用mapToInt()转为IntStream流来求和
        Integer sum2 = items.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Test
    public void test5() {
        // TODO 7、flatMap (对流扁平化处理)
        List<List<String>> lists = Lists.newArrayList(Arrays.asList("AB", "Amla", "Faf"), Arrays.asList("Virat", "Dhoni", "Jadeja"));
        // 以下两种写法都可以。flatMap其实就相当于将List<List<String>>转为List<String>，将外层的List元素全部依次放入List<String>中
        List<String> flatMapList1 = lists.stream()
                .flatMap(pList -> pList.stream())
                .collect(Collectors.toList());
        List<String> flatMapList2 = lists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        log.error(flatMapList2.toString());

        // TODO 8、List转Map(第一个参数：作为返回Map的key值  第二个参数：作为返回Map的value值)
        Map<Long, User> collect1 = users.stream()
                .filter(ObjectUtil::isNotEmpty)
                .collect(Collectors.toMap(User::getId, y -> y));
        log.error(collect1.toString());
        // 这里第三个参数表示：当key值重复了，取下标靠前的值;如果这里(v1, v2) -> v2，则表示key值重复了，取下标靠后的值
        Map<Long, User> collect2 = users.stream()
                .filter(ObjectUtil::isNotEmpty)
                .collect(Collectors.toMap(User::getId, y -> y, (v1, v2) -> v2));
        log.error(collect2.toString());
    }
}
