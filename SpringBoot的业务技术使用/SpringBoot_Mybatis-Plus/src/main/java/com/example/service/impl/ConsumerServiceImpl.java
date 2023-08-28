package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ConsumerMapper;
import com.example.entity.Consumer;
import com.example.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Consumer)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-08-28 22:28:21
 */
@Service("consumerService")
@RequiredArgsConstructor
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements ConsumerService {

    private final ConsumerMapper consumerMapper;

    /**
     * 根据Id查询
     */
    public Consumer selectById() {
        Consumer consumer = consumerMapper.selectById(10);
        return consumer;
    }

    /**
     * 根据Id集合查询
     */
    public List<Consumer> selectByIds() {
        List<Integer> idsList = Arrays.asList(4, 8, 13, 26); // 获取id集合
        return consumerMapper.selectBatchIds(idsList);
    }

    /**
     * 根据条件查询
     */
    public List<Consumer> selectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        // columnMap.put("name","三体");	 // 必须与数据库中的对应，如果没有会报错
        columnMap.put("age", 73);        // 键是数据库中的列  where age= 27
        return consumerMapper.selectByMap(columnMap);
    }

    /**
     * 条件构造器查询
     * 需求1： 名字中包含月并且年龄小于40
     * name like '%月%' and age<40
     */
    public List<Consumer> selectByWrapper1() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.like("name", "月").lt("age", 40);
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求2： 名字中包含月并且年龄大于等于20且小于等于40并且email不为空
     * name like '%月%' and age between 20 and 40 and email is not null
     */
    public List<Consumer> selectByWrapper2() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.like("name", "月").between("age", 20, 40).isNotNull("email");
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求3： 名字为伍姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列；
     * name like '伍%' or age>= 25 order by age desc,id asc
     */
    public List<Consumer> selectByWrapper3() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.likeRight("name", "伍").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求4： 创建日期为2023年8月29日并且直属上级为名字为王姓
     * data_format(create_time,'%Y-%m-%d') and manager_id in (select id from Consumer where name like '王%')
     */
    public List<Consumer> selectByWrapper4() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = 2023-08-29")
                .inSql("manager_id", "select id from Consumer where name like '王%'");
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求5： 名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    public List<Consumer> selectByWrapper5() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求6： 名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '王%' or (age<40 and age>20 and email is not null)
     */
    public List<Consumer> selectByWrapper6() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.likeRight("name", "王").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
        return consumerMapper.selectList(queryWrapper);
    }


    /**
     * 条件构造器查询
     * 需求7： (年龄小于40或邮箱不为空)并且名字为王姓
     * (age<40 or email is not null) and name like '王%'
     */
    public List<Consumer> selectByWrapper7() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求8： 年龄为30、31、34、35
     * age in (30、31、34、35)
     */
    public List<Consumer> selectByWrapper8() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35));
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求9：只返回满足条件的其中一条语句即可
     * limit 1
     */
    public List<Consumer> selectByWrapper9() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1");
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求10： 名字中包含雨并且年龄小于40   只查询id,name两个字段
     * name like '%雨%' and age<40
     */
    public List<Consumer> selectByWrapper10() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.select("id", "name").like("name", "雨").lt("age", 40);
        return consumerMapper.selectList(queryWrapper);
    }


    /**
     * 条件构造器查询
     * 需求11： 名字中包含雨并且年龄小于40   只查询部分字段使用排除法
     * select id,name,age,email from Consumer where like '%雨%' and age<40
     */
    public List<Consumer> selectByWrapper11() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.like("name", "雨")
                .lt("age", 40)
                .select(Consumer.class
                        , info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id"));
        return consumerMapper.selectList(queryWrapper);
    }
}



