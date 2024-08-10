package com.example.controller;

import com.example.annotation.GetVal;
import com.example.pojo.User;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collector;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-10 15:54
 * @apiNote TODO 解析集合
 */
@RestController
public class CollectionSpELController {

    // TODO List 集合，获得所有符合要求的数据
    //      从集合按条件筛选生成新集合：.?[selectionExpression]
    @RequestMapping("/collector1")
    @GetVal(value = "@{#user.phone.?[#this>50]}")
    public String objects1(@RequestBody User user){
        return "SpEL表达式结果：" +
                user.getPhone().stream().filter(po -> po>50).toList();
    }

    // TODO 数组无法筛选元素数据，只能通过使用特殊的T运算符来指定java.util.Arrays的实例转为 List 集合，再进行筛选
    @RequestMapping("/collector2")
    @GetVal(value = "@{T(java.util.Arrays).asList(#user.money).?[#this>50]}")
    public String objects2(@RequestBody User user){
        return "SpEL表达式结果：" +
                Arrays.stream(user.getMoney()).filter(po -> po>50).toList();
    }

    // TODO List 集合，获得匹配的第一个（非排序[可使用T运算符的方式进行排序后再筛选]）
    //  从集合按条件筛选后取第一个元素：.^[selectionExpression]
    @RequestMapping("/collector3")
    @GetVal(value = "@{#user.phone.^[#this>50]}")
    public String objects3(@RequestBody User user){
        return "SpEL表达式结果：" +
                user.getPhone().stream().filter(po -> po>50).toList().getFirst();
    }

    // TODO List 集合，获得匹配的最后一个（非排序[可使用T运算符的方式进行排序后再筛选]）
    //  从集合按条件筛选后取第一个元素：.$[selectionExpression]
    @RequestMapping("/collector4")
    @GetVal(value = "@{#user.phone.$[#this>50]}")
    public String objects4(@RequestBody User user){
        return "SpEL表达式结果：" +
                user.getPhone().stream().filter(po -> po>50).toList().getLast();
    }
}
