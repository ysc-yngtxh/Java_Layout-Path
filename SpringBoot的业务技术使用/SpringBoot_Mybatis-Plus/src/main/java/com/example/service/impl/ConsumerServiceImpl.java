package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ConsumerMapper;
import com.example.entity.Consumer;
import com.example.service.ConsumerService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Consumer)表服务实现类
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
        return consumerMapper.selectById(10);
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
        // columnMap.put("username","三体");	 // 必须与数据库中的对应，如果没有会报错
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
        queryWrapper.like("username", "月").lt("age", 40);
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求2： 名字中包含月并且年龄大于等于20且小于等于40并且email不为空
     * name like '%月%' and age between 20 and 40 and email is not null
     */
    public List<Consumer> selectByWrapper2() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.like("username", "月").between("age", 20, 50).isNotNull("email");
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求3： 名字为伍姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列；
     * name like '伍%' or age>= 25 order by age desc,id asc
     */
    public List<Consumer> selectByWrapper3() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.likeRight("username", "伍").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求4： 创建日期为2023年8月29日并且直属上级为名字为伍姓
     * data_format(create_time,'%Y-%m-%d') and manager_id in (select id from Consumer where name like '伍%')
     */
    public List<Consumer> selectByWrapper4() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.apply("date_format(created_date,'%Y-%m-%d') = 2023-08-29")
                .inSql("manager_id", "select id from Consumer where username like '伍%'");
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求5： 名字为伍姓并且（年龄小于40或邮箱不为空）
     * name like '伍%' and (age<40 or email is not null)
     */
    public List<Consumer> selectByWrapper5() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.likeRight("username", "伍").and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 条件构造器查询
     * 需求6： 名字为伍姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '伍%' or (age<40 and age>20 and email is not null)
     */
    public List<Consumer> selectByWrapper6() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.likeRight("username", "伍").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
        return consumerMapper.selectList(queryWrapper);
    }


    /**
     * 条件构造器查询
     * 需求7： (年龄小于40或邮箱不为空)并且名字为伍姓
     * (age<40 or email is not null) and name like '伍%'
     */
    public List<Consumer> selectByWrapper7() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email")).likeRight("username", "伍");
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
     * name like '%月%' and age<40
     */
    public List<Consumer> selectByWrapper10() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.select("id", "username").like("name", "月").lt("age", 40);
        return consumerMapper.selectList(queryWrapper);
    }


    /**
     * 条件构造器查询
     * 需求11： 名字中包含月并且年龄小于40  并且排除created_date、updated_date两个字段
     * select id,username,age,email from Consumer where like '%月%' and age<40
     */
    public List<Consumer> selectByWrapper11() {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        queryWrapper.like("username", "月")
                .lt("age", 40)
                .select(Consumer.class, info ->
                        !info.getColumn().equals("created_date") && !info.getColumn().equals("updated_date"));
        return consumerMapper.selectList(queryWrapper);
    }

    /**
     * 校验传入参数不为空的时候，进行模糊查询
     * condition作用：当它的值为true的时候，这个方法才会执行
     */
    public List<Consumer> condition(String name, String email) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>();
        // if(StringUtils.isNotEmpty(name)){
        //   queryWrapper.like("name", name);
        // }
        // if(StringUtils.isNotEmpty(email)){
        //	 queryWrapper.like("email", email);
        // }
        // 下面的写法可以替代上面的这两个逻辑判断
        queryWrapper.likeRight(StringUtils.isNotEmpty(name), "username", name)
                .likeLeft(StringUtils.isNotEmpty(email), "email", email);
        return consumerMapper.selectList(queryWrapper);
    }

    /**实体作为条件构造器构造方法的参数*/
    public List<Consumer> selectByWrapperEntity(){
        Consumer consumer = new Consumer();
        consumer.setUsername("双月之城");
        consumer.setAge(5);
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<Consumer>(consumer);
        queryWrapper.like("username", "月").lt("age", 40);	// 这条语句写上，会与whereUser这条同时生效；
        return consumerMapper.selectList(queryWrapper);
    }
}



