package com.example.集合实用Api;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/11/26 14:54
 */
public class CustomerCollection {
    public static final Log log = LogFactory.get(CustomerCollection.class);

    @Test
    public void test1() {
        // 1、使用集合工具类(返回结果为true：表示添加成功；反之失败)
        List<Object> objectArrayList = new ArrayList<>();
        boolean address = Collections.addAll(objectArrayList, "address", "1234");

        // 2、使用Util包的数组转集合(不可变集合)
        List<String> asList = Arrays.asList("address", "1234");

        // 3、使用Stream流
        List<Object> collect = Stream.of("address", "1234").collect(Collectors.toList());
    }
    @Test
    public void test2() {
        // ImmutableList是一个不可变、线程安全的列表集合(静态方法、集合元素类型保持一致)
        ImmutableList<String> immutableList1 = ImmutableList.of("address", "1234");
        // ImmutableList是一个不可变、线程安全的列表集合(静态内部类、集合元素类型可不相同)
        ImmutableList<Object> immutableList2 = ImmutableList.builder().add("address").add(123).build();
        // 使用copy
        ImmutableList<String> copyOfList = ImmutableList.copyOf(immutableList1);

        // ImmutableMap是一个不可变、线程安全的Map集合(静态方法、集合元素类型保持一致)
        ImmutableMap<String, String> immutableMap1 = ImmutableMap.of("name", "游诗成", "age", "25");
        // ImmutableMap是一个不可变、线程安全的Map集合(静态内部类、集合元素类型可不相同)
        ImmutableMap<Object, Object> immutableMap2 = ImmutableMap.builder().put("name", "游诗成").put("age", 25).build();
        // 使用copy
        ImmutableMap<String, String> copyOfMap = ImmutableMap.copyOf(immutableMap1);

        // 创建集合对象(可变集合)
        List<String> arrayList = Lists.newArrayList("123", "456");
        Map<String, Integer> hashMap = Maps.newHashMap(ImmutableMap.of("name", 25));
    }
    @Test
    public void test3() {
        // TODO 集合引用小问题
        /**
         * 为什么要用List list = new ArrayList(),而不用ArrayList list = new ArrayList()呢?
         */
        // 1、问题就在于List接口有多个实现类，现在你用的是ArrayList，也许哪一天你需要换成其它的实现类，如LinkedList或者Vector等等，
        //    这时你只要改变这一行就行了： List list = new LinkedList(); 其它使用了list地方的代码根本不需要改动。
        // 2、假设你开始用ArrayList list = new ArrayList(), 这下你有的改了，特别是如果你使用了ArrayList实现类特有的方法和属性。
        // 3、这样的好处是为了代码的可维护性，可复用性，可扩展性以及灵活性，再者就是这符合了里氏代换原则和开闭原则。

        // 集合拆分partition
        List<String> arrayList1 = Lists.newArrayList("123", "456", "789"
                , "101112", "131415", "161718", "192021", "222324", "252627", "282930");
        List<List<String>> partition = Lists.partition(arrayList1, 2);
        log.info(partition.toString());

        // 不可变的，安全的空集合对象
        List<Object> emptyList = Collections.EMPTY_LIST;
    }
}
