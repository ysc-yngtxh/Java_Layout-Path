package com.example.补充Stream方法及增强Stream依赖包Api;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.vo.ModelView;
import com.example.vo.Models;
import com.example.vo.User;
import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import one.util.streamex.DoubleStreamEx;
import one.util.streamex.EntryStream;
import one.util.streamex.IntStreamEx;
import one.util.streamex.StreamEx;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2022/11/28 0:03
 */
public class StreamExApi {
    public static final Log log = LogFactory.get(StreamExApi.class);
    List<User> users = Arrays.asList(
            new User(null, null, null, 0, null
                    ,new Models(new ModelView("WUHAN"),null),null)
            ,new User(1L, "YouShiCheng", "google@163.com", 25, null
                    ,new Models(new ModelView("SHENZHEN"),null),null));

    @Test
    public void test1() {
        // TODO 1、简单使用StreamEx映射name属性输出为List(不需要collect)
        List<String> userNames = StreamEx.of(users)
                .map(User::getName)
                .toList();
        log.info("TODO-1" + userNames);

        // TODO 2、StreamEx分组(不需要collect)
        Map<Models, List<User>> modelsUsers = StreamEx.of(users)
                .groupingBy(User::getModels);
        log.info(modelsUsers.toString());

        // TODO 3、StreamEx拼接(不需要collect)
        String joining = StreamEx.of(1, 2, 3)
                .joining("; ");
        log.info(joining);

        // TODO 4、StreamEx的select方法
        List<Models> modelsList = StreamEx.of(users)
                .select(Models.class)
                .toList();
        log.info(modelsList.toString());

        // TODO 5、StreamEx中的nonNull方法过滤为null的值，并作为iterable
        for (String line : StreamEx.of(users).map(User::getName).nonNull()) {
            System.out.println(line);
        }
    }
    @Test
    public void test2() {
        // TODO 6、通过IntStreamEx流将数据业务处理再转为short数组
        short[] src = {1, 2, 3};
        short[] ints = IntStreamEx.of(src)
                .map(x -> x * 2)
                .toShortArray();
        log.info(Arrays.toString(ints));

        // TODO 7、pairMap按顺序取两个元素进行业务处理
        double[] srcDouble = {1,2,3};
        double[] doubles = DoubleStreamEx.of(srcDouble)
                .pairMap((a, b) -> b - a)
                .toArray();
        log.info("TODO-7"+Arrays.toString(doubles));

        // TODO 8、通过ofKeys()方法，将Map里的所有key取出来，并通过Predicate断言函数方法进行过滤
        Set<String> nonNullRoles = StreamEx.ofKeys(
                        ImmutableMap.of("first", new Models()
                                ,"second", new Models(new ModelView("HUBEI"),null)
                                ,"null", new Models())
                        , Objects::nonNull)
                .toSet();
        log.info("TODO-8"+nonNullRoles.toString());
    }
    @Test
    @SneakyThrows
    public void test3() {
        // TODO 9、StreamEx读文件
        BufferedReader bf =
                new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/main/java/com/example/vo/User.java"));
        StreamEx.ofLines(bf)
                .remove(String::isEmpty)
                .forEach(System.out::println);

        // TODO 10、StreamEx提供了append()方法来合并流
        Stream<Integer> stream1 = Stream.of(1, 3, 5);
        Stream<Integer> stream2 = Stream.of(2, 4, 6);
        Stream<Integer> stream3 = Stream.of(18, 15, 36);
        Stream<Integer> stream4 = Stream.of(99);
        Stream<Integer> resultingStream = StreamEx.of(stream1)
                .append(stream2)
                .append(stream3)
                .append(stream4);
        log.info(resultingStream.collect(Collectors.toList()).toString());

        // TODO 11、StreamEx还提供了prepend()方法，可以将流中元素加在其它流之前
        List<String> stringList = StreamEx.of(users)
                .map(User::getName)
                .prepend("begin--")
                .append("--end")
                .toList();
        log.info(stringList.toString());
    }
    @Test
    public void test4() {
        /** ========================================StreamEx与EntryStream分割线=====================================*/

        // TODO 12、首先通过flatMapValues方法将value值List<User>扁平化为User类型，再通过invert方法将key、value值对调
        Map<User, List<Models>> users2roles =
                EntryStream.of(ImmutableMap.of(new Models(), Collections.singletonList(new User())))
                        .flatMapValues(List::stream)
                        .invert()
                        .grouping();
        log.info(users2roles.toString());

        // TODO 13、Map转流，处理key、value的业务逻辑，最后返回Map
        Map<User, String> mapToString = EntryStream.of(users2roles)
                .mapKeys(k -> {
                    k.setAge(k.getAge() + 12);
                    return k;
                })
                .mapValues(String::valueOf)
                .toMap();
        log.info(mapToString.toString());

        // TODO 14、EntryStream使用索引对流进行迭代
        String[] names = {"Afrim", "Bashkim", "Besim", "Lulzim", "Durim", "Shpetim"};
        // 过滤出下标为偶数的value值
        EntryStream.of(names)
                .filterKeyValue((index, name) -> index % 2 == 0)
                .values()
                .toList().forEach(System.out::println);
        // 可以在对应的下标中处理value值
        EntryStream.of(names)
                .mapKeyValue((index, name) -> index+1+"、"+name)
                .toList().forEach(System.out::println);

        // TODO 以上的StreamEx用于处理List，EntryStream用于处理Map
    }
}
