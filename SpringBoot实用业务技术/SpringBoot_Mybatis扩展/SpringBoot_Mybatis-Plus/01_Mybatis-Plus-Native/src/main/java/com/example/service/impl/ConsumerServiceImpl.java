package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Consumer;
import com.example.mapper.ConsumerMapper;
import com.example.service.ConsumerService;
import com.google.common.collect.ImmutableMap;
import io.micrometer.common.util.StringUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * (Consumer)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-08-28 22:30:20
 */
@Service("consumerService")
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

	private final ConsumerMapper consumerMapper;

	// TODO 第一部分：查询演示

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
		// columnMap.put("user_name", "三体");  // 必须与数据库中的对应，如果没有会报错
		columnMap.put("age", 73);              // 键是数据库中的列 where age= 27
		return consumerMapper.selectByMap(columnMap);
	}

	/**
	 * 根据条件查询,返回一个 Map 类型数据
	 */
	public List<Map<String, Object>> selectMaps() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("user_name", "一");
		// 返回的是一个List<Map<String, Object>>类型，我们的数据对应 Map<属性名, 属性值>
		// 需要注意的是 这里返回的属性名是数据库表字段，而不是映射的实体类属性字段(例如：返回数据create_data而不是createDate)
		// 我们执行sql语句返回的数据本来就是数据库表字段，这里resultType 设置为用Map承接出来而已。
		// 使用 Map 场景：比如我们使用聚合函数返回的字段不存在于实体类，那这个时候就比较适合用 Map 类型作为返回类型
		return consumerMapper.selectMaps(queryWrapper);
	}

	/**
	 * 条件构造器查询
	 * 需求1： 名字中包含月并且年龄小于40
	 * name like '%月%' and age<40
	 */
	public List<Consumer> selectByWrapper1() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("user_name", "月").lt("age", 40);
		return consumerMapper.selectList(queryWrapper);
	}

	/**
	 * 条件构造器查询
	 * 需求2： 名字中包含月并且年龄大于等于20且小于等于40并且email不为空
	 * name like '%月%' and age between 20 and 40 and email is not null
	 */
	public List<Consumer> selectByWrapper2() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("user_name", "月").between("age", 20, 50).isNotNull("email");
		return consumerMapper.selectList(queryWrapper);
	}

	/**
	 * 条件构造器查询
	 * 需求3： 名字为伍姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列；
	 * name like '伍%' or age>= 25 order by age desc,id asc
	 */
	public List<Consumer> selectByWrapper3() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		// likeRight()方法表示查询字段的右边部分是模糊查询
		queryWrapper.likeRight("user_name", "伍").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
		return consumerMapper.selectList(queryWrapper);
	}

	/**
	 * 条件构造器查询
	 * 需求4： 创建日期为2023年8月29日并且直属上级为名字为伍姓
	 * data_format(create_time,'%Y-%m-%d') and superior_id in (select id from consumer where name like '伍%')
	 */
	public List<Consumer> selectByWrapper4() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.apply("date_format(created_date,'%Y-%m-%d') = '2023-08-29'")
		            .inSql("superior_id", "select id from consumer where user_name like '伍%'");
		return consumerMapper.selectList(queryWrapper);
	}

	/**
	 * 条件构造器查询
	 * 需求5： 名字为伍姓并且（年龄小于40或邮箱不为空）
	 * name like '伍%' and (age<40 or email is not null)
	 */
	public List<Consumer> selectByWrapper5() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.likeRight("user_name", "伍").and(wq -> wq.lt("age", 40).or().isNotNull("email"));
		return consumerMapper.selectList(queryWrapper);
	}

	/**
	 * 条件构造器查询
	 * 需求6： 名字为伍姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
	 * name like '伍%' or (age<40 and age>20 and email is not null)
	 */
	public List<Consumer> selectByWrapper6() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.likeRight("user_name", "伍").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
		return consumerMapper.selectList(queryWrapper);
	}


	/**
	 * 条件构造器查询
	 * 需求7： (年龄小于40或邮箱不为空)并且名字为伍姓
	 * (age<40 or email is not null) and name like '伍%'
	 */
	public List<Consumer> selectByWrapper7() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email")).likeRight("user_name", "伍");
		return consumerMapper.selectList(queryWrapper);
	}

	/**
	 * 条件构造器查询
	 * 需求8： 年龄为30、31、34、35
	 * age in (30、31、34、35)
	 */
	public List<Consumer> selectByWrapper8() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.in("age", Arrays.asList(30, 31, 34, 35));
		return consumerMapper.selectList(queryWrapper);
	}

	/**
	 * 条件构造器查询
	 * 需求9：只返回满足条件的其中一条语句即可
	 * limit 1
	 */
	public List<Consumer> selectByWrapper9() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1");
		return consumerMapper.selectList(queryWrapper);
	}

	/**
	 * 条件构造器查询
	 * 需求10： 名字中包含雨并且年龄小于40   只查询id,name两个字段
	 * select id,name from *** where like '%月%' and age<40
	 */
	public List<Consumer> selectByWrapper10() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("id", "user_name").like("user_name", "月").lt("age", 40);
		return consumerMapper.selectList(queryWrapper);
	}


	/**
	 * 条件构造器查询
	 * 需求11： 名字中包含'月'并且年龄小于40  并且排除created_date、updated_date两个字段
	 * select id,username,age,email from Consumer where like '%月%' and age<40
	 */
	public List<Consumer> selectByWrapper11() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("user_name", "月")
		            .lt("age", 40)
		            .select(Consumer.class, info ->
				            !info.getColumn().equals("created_date") && !info.getColumn().equals("updated_date"));
		return consumerMapper.selectList(queryWrapper);
	}

	// TODO 第二部分：高级查询

	/**
	 * condition作用：当它的值为true的时候，这个方法才会执行
	 * 每个sql方法的都有重载方法的第一个入参为boolean dependency，表示该条件是否加入最后生成的sql中
	 */
	public List<Consumer> condition(String name, String email) {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		// if(StringUtils.isNotEmpty(name)){
		//   queryWrapper.like("user_name", name);
		// }
		// if(StringUtils.isNotEmpty(email)){
		//	 queryWrapper.like("email", email);
		// }
		// 下面的写法可以替代上面的这两个逻辑判断
		queryWrapper.likeRight(StringUtils.isNotEmpty(name), "user_name", name)
		            .likeLeft(StringUtils.isNotEmpty(email), "email", email);
		return consumerMapper.selectList(queryWrapper);
	}

	/**
	 * 实体类作为条件构造器构造方法的参数
	 * SELECT * FROM consumer WHERE user_name='双月之城' AND age=5 AND (user_name LIKE '%月%' AND age < 40)
	 */
	public List<Consumer> selectByWrapperEntity() {
		Consumer tb1Consumer = new Consumer();
		tb1Consumer.setUserName("双月之城");
		tb1Consumer.setAge(5);
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>(tb1Consumer);
		queryWrapper.like("user_name", "月").lt("age", 40);    // 这条语句写上，会与where User这条同时生效；
		return consumerMapper.selectList(queryWrapper);
	}

	/**
	 * 过滤掉传入的某些指定的查询条件（这里过滤掉 user_name='王天风' 的WHERE子句条件）
	 * SELECT * FROM consumer WHERE (age = ?)
	 */
	public List<Consumer> selectByWrapperAllEq() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		Map<String, Object> params = new HashMap<>();
		params.put("user_name", "王天风");
		params.put("age", 44);
		// queryWrapper.allEq(params);
		// 过滤查询
		queryWrapper.allEq((k, v) -> !k.equals("user_name"), params);
		return consumerMapper.selectList(queryWrapper);
	}

	/**
	 * 按照性别分组，查询每组的平均年龄、最大年龄、最小年龄；并且只取年龄之和小于 500000 的组
	 * SELECT avg(age) AS avg_age,max(age) AS max_age,min(age) AS min_age FROM consumer GROUP BY sex HAVING sum(age)<?
	 */
	public List<Map<String, Object>> selectByWrapperMap() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("avg(age) AS avg_age", "max(age) AS max_age", "min(age) AS min_age")
		            .groupBy("sex").having("sum(age)<{0}", 500000);
		return consumerMapper.selectMaps(queryWrapper);
	}

	/**
	 * 使用注解方式自定义查询，
	 * 第一种：使用全 Sql语句，使用 #{} 接收传参
	 * 第二种：传参为一个Wrapper；并且注解使用${}解析 Wrapper
	 */
	public Map<String, List<Consumer>> selectCustomAnnotation() {
		// 第一种方式：注解形式sql使用#{}接受where条件传参
		List<Consumer> annotationParam = consumerMapper.selectCustomAnnotationParam("灵%", 40, "email");
		// 第二种方式：注解形式sql获取 Wrapper对象参数，解析后进行拼接
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("*").likeRight("user_name", "灵").and(w -> w.lt("age", 40).or().isNotNull("email"));
		List<Consumer> annotationWrapper = consumerMapper.selectCustomAnnotationWrapper(queryWrapper);

		Map<String, List<Consumer>> map = new HashMap<>();
		map.put("annotationParam", annotationParam);
		map.put("annotationWrapper", annotationWrapper);
		return map;
	}

	/**
	 * Lambda条件过滤器
	 * 在编写QueryWrapper不难发现使用，里面构建的条件使用了大量的魔法值和硬编码，不符合编写规范
	 * 因此，我们在使用 Wrapper构建器 尽量选择使用 LambdaQueryWrapper构建器
	 */
	public List<Consumer> selectLambdaQuery() {
		// lambda的创建方式有三种，如下所示：
		// LambdaQueryWrapper<Consumer> lambdaQuery = new LambdaQueryWrapper<Consumer>();
		// LambdaQueryWrapper<Consumer> lambdaQuery = new QueryWrapper<Consumer>().lambda;
		// 第三种：
		LambdaQueryWrapper<Consumer> lambdaQuery = Wrappers.<Consumer>lambdaQuery();
		lambdaQuery.like(Consumer::getUserName, "灵").lt(Consumer::getAge, 40);
		return consumerMapper.selectList(lambdaQuery);
	}

	// TODO 第三部分：分页查询(必须注入Mybatis-Plus的分页插件，否则sql语句中不存在limit拼接或者无法获取分页总页数等数据)
	public Page<Consumer> selectPage() {
		QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("phone", "15623041568");
		// 使用 Page 分页查询正常是两条SQL语句，一条是查询总记录数，一条是查询具体数据的
		// Page两个参数：current表示当前页 值默认是1，从1开始，不是0。size表示每页显示条数。
		Page<Consumer> page1 = new Page<>(1, 4);
		Page<Consumer> selectPage1 = consumerMapper.selectPage(page1, queryWrapper);
		selectPage1.getRecords().forEach(System.out::println);

		System.out.println("page1 当前页：" + selectPage1.getCurrent());
		System.out.println("page1 总页数：" + selectPage1.getPages());
		System.out.println("page1 记录数：" + selectPage1.getTotal());
		System.out.println("page1 是否有上一页：" + selectPage1.hasPrevious());
		System.out.println("page1 是否有下一页：" + selectPage1.hasNext());

		// Page 重载方法，第三个参数如果为false则不查询总记录数,只查询具体数据的
		Page<Map<String, Object>> page2 = new Page<>(1, 4, false);
		// selectMapsPage()方法返回分页数据为一个 Map 类型
		Page<Map<String, Object>> selectPage2 = consumerMapper.selectMapsPage(page2, queryWrapper);
		selectPage2.getRecords().forEach(System.out::println);

		System.out.println("page2 当前页：" + selectPage2.getCurrent());
		System.out.println("page2 总页数：" + selectPage2.getPages());
		System.out.println("page2 记录数：" + selectPage2.getTotal());
		System.out.println("page2 是否有上一页：" + selectPage2.hasPrevious());
		System.out.println("page2 是否有下一页：" + selectPage2.hasNext());
		return selectPage1;
	}

	// TODO 第四部分：更新操作
	public void updateConsumer() {
		Consumer tb1Consumer1 = Consumer.builder().id(28).age(26).email("dfgsd@163.com").build();
		int updateById = consumerMapper.updateById(tb1Consumer1);
		System.out.println(updateById);

		LambdaUpdateWrapper<Consumer> updateWrapper = new LambdaUpdateWrapper<Consumer>().gt(Consumer::getAge, 30);
		Consumer tb1Consumer2 = Consumer.builder().id(28).age(26).email("bjdfhd@qq.com").build();
		int update = consumerMapper.update(tb1Consumer2, updateWrapper);
		System.out.println(update);
	}

	// TODO 第五部分：删除操作
	public void deleteConsumer() {
		int deleteById = consumerMapper.deleteById(28);
		System.out.println(deleteById);
		int deleteByMap = consumerMapper.deleteByMap(ImmutableMap.of("username", "测试", "age", 26));
		System.out.println(deleteByMap);
	}

	// TODO 第五部分：插入操作
	public Consumer insertConsumer() {
		Consumer tb1Consumer = Consumer.builder()
		                               .superiorId(1).userName("测试").passWord("616165").alias("法师")
		                               .sex(1).age(24).phone("15623041568").address("安徽省合肥市长丰县")
		                               .email("yamwaihan@outlook.com").deleteFlag(0)
		                               .createdDate(new Date()).updatedDate(new Date())
		                               .build();
		int insert = consumerMapper.insert(tb1Consumer);
		System.out.println(insert);
		return tb1Consumer;
	}
}
