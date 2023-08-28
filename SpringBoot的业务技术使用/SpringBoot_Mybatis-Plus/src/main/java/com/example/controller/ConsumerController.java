package com.example.controller;

import com.example.entity.Consumer;
import com.example.service.impl.ConsumerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (Consumer)表控制层
 * @author 游家纨绔
 * @since 2023-08-28 22:28:21
 */
@RestController
@RequestMapping("consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerServiceImpl consumerService;

    /**
     * 根据Id查询
     */
    @RequestMapping("/selectById")
    public Consumer selectById() {
        Consumer consumer = consumerService.selectById();
        return consumer;
    }

    /**
     * 根据Id集合查询
     */
    @RequestMapping("/selectByIds")
    public List<Consumer> selectByIds() {
        List<Consumer> consumerList = consumerService.selectByIds();
        return consumerList;
    }

    /**
     * 根据条件查询
     */
    @RequestMapping("/selectByMap")
    public List<Consumer> selectByMap() {
        List<Consumer> consumerList = consumerService.selectByMap();
        return consumerList;
    }

    /**
     * 条件构造器查询
     * 需求1： 名字中包含月并且年龄小于40
     * name like '%月%' and age<40
     */
    @RequestMapping("/selectByWrapper1")
    public List<Consumer> selectByWrapper1() {
        List<Consumer> consumerList = consumerService.selectByWrapper1();
        return consumerList;
    }

    /**
     * 条件构造器查询
     * 需求2： 名字中包含月并且年龄大于等于20且小于等于40并且email不为空
     * name like '%月%' and age between 20 and 40 and email is not null
     */
    @RequestMapping("/selectByWrapper2")
    public List<Consumer> selectByWrapper2() {
        List<Consumer> consumerList = consumerService.selectByWrapper2();
        return consumerList;
    }

    /**
     * 条件构造器查询
     * 需求3： 名字为伍姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列；
     * name like '伍%' or age>= 25 order by age desc,id asc
     */
    @RequestMapping("/selectByWrapper3")
    public List<Consumer> selectByWrapper3() {
        List<Consumer> consumerList = consumerService.selectByWrapper3();
        return consumerList;
    }

    /**
     * 条件构造器查询
     * 需求4： 创建日期为2023年8月29日并且直属上级为名字为王姓
     * data_format(create_time,'%Y-%m-%d') and manager_id in (select id from Consumer where name like '王%')
     */
    @RequestMapping("/selectByWrapper4")
    public List<Consumer> selectByWrapper4() {
        List<Consumer> consumerList = consumerService.selectByWrapper4();
        return consumerList;
    }

    /**
     * 条件构造器查询
     * 需求5： 名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @RequestMapping("/selectByWrapper5")
    public List<Consumer> selectByWrapper5() {
        List<Consumer> consumerList = consumerService.selectByWrapper5();
        return consumerList;
    }

    /**
     * 条件构造器查询
     * 需求6： 名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '王%' or (age<40 and age>20 and email is not null)
     */
    @RequestMapping("/selectByWrapper6")
    public List<Consumer> selectByWrapper6() {
        List<Consumer> consumerList = consumerService.selectByWrapper6();
        return consumerList;
    }


    /**
     * 条件构造器查询
     * 需求7： (年龄小于40或邮箱不为空)并且名字为王姓
     * (age<40 or email is not null) and name like '王%'
     */
    @RequestMapping("/selectByWrapper7")
    public List<Consumer> selectByWrapper7() {
        List<Consumer> consumerList = consumerService.selectByWrapper7();
        return consumerList;
    }

    /**
     * 条件构造器查询
     * 需求8： 年龄为30、31、34、35
     * age in (30、31、34、35)
     */
    @RequestMapping("/selectByWrapper8")
    public List<Consumer> selectByWrapper8() {
        List<Consumer> consumerList = consumerService.selectByWrapper8();
        return consumerList;
    }

    /**
     * 条件构造器查询
     * 需求9：只返回满足条件的其中一条语句即可
     * limit 1
     */
    @RequestMapping("/selectByWrapper9")
    public List<Consumer> selectByWrapper9() {
        List<Consumer> consumerList = consumerService.selectByWrapper9();
        return consumerList;
    }

    /**
     * 条件构造器查询
     * 需求10： 名字中包含雨并且年龄小于40   只查询id,name两个字段
     * name like '%雨%' and age<40
     */
    @RequestMapping("/selectByWrapper10")
    public List<Consumer> selectByWrapper10() {
        List<Consumer> consumerList = consumerService.selectByWrapper10();
        return consumerList;
    }


    /**
     * 条件构造器查询
     * 需求11： 名字中包含雨并且年龄小于40   只查询部分字段使用排除法
     * select id,name,age,email from Consumer where like '%雨%' and age<40
     */
    @RequestMapping("/selectByWrapper11")
    public List<Consumer> selectByWrapper11() {
        List<Consumer> consumerList = consumerService.selectByWrapper11();
        return consumerList;
    }
}

