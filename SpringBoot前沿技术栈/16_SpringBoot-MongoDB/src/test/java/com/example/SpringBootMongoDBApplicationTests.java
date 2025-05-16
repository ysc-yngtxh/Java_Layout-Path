package com.example;

import com.example.pojo.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
class SpringBootMongoDBApplicationTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	// 添加操作
	@Test
	void contextLoads() {
		User user = new User();
		user.setAge(20);
		user.setName("test");
		user.setEmail("123@qq.com");
		user.setCreateDate(LocalDateTime.now().toString());
		;
		User user1 = mongoTemplate.insert(user);
		System.out.println(user1);
	}

	// 查询所有记录
	@Test
	public void findAll() {
		List<User> all = mongoTemplate.findAll(User.class);
		System.out.println(all);
	}

	// 根据id查询
	@Test
	public void findId() {
		User user = mongoTemplate.findById("668aad54f52a9fef290bdf5f", User.class);
		System.out.println(user);
	}

	// 条件查询
	@Test
	public void findUserList() {
		Query query = new Query(Criteria.where("name").is("test").and("age").is(20));
		List<User> users = mongoTemplate.find(query, User.class);
		System.out.println(users);
	}

	// 模糊条件查询
	@Test
	public void findLikeUserList() {
		// name like test
		String name = "est";
		String regex = String.format("%s%s%s", "^.*", name, ".*$");
		// 1、在使用Pattern.compile函数时，可以加入控制正则表达式的匹配行为的参数：
		//    Pattern Pattern.compile(String regex, int flag)
		// 2、regex设置匹配规则
		// 3、Pattern.CASE_INSENSITIVE,这个标志能让表达式忽略大小写进行匹配。
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		// 创建一个query对象（用来封装所有条件对象)，再创建一个criteria对象（用来构建条件）
		Query query = new Query( // 构建查询条件
		                         Criteria.where("name").regex(pattern));
		List<User> users = mongoTemplate.find(query, User.class);
		System.out.println(users);
	}

	// 分页查询（带条件）
	@Test
	public void pageLikeUserList() {
		int pageNo = 1;  // 设置当前页
		int pageSize = 3;// 设置每页显示的记录数

		// 条件构建
		String name = "est";
		String regex = String.format("%s%s%s", "^.*", name, ".*$");
		// 1、在使用Pattern.compile函数时，可以加入控制正则表达式的匹配行为的参数：
		//    Pattern Pattern.compile(String regex, int flag)
		// 2、regex设置匹配规则
		// 3、Pattern.CASE_INSENSITIVE,这个标志能让表达式忽略大小写进行匹配。
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		// 创建一个query对象（用来封装所有条件对象)，再创建一个criteria对象（用来构建条件）
		Query query = new Query(
				// 构建查询条件
				Criteria.where("name").regex(pattern));

		// 分页构建
		// 查询数来集合（表）中的总记录数
		long count = mongoTemplate.count(query, User.class);
		List<User> users = mongoTemplate.find(
				query.skip((pageNo - 1) * pageSize).limit(pageSize), User.class);
		System.out.println(count);
		System.out.println(users);
	}

	// 修改操作
	@Test
	public void updateUser() {
		// 根据id查询
		User user = mongoTemplate.findById("60b4f89db925b61fbf529591", User.class);
		// 修改值
		user.setName("test_02");
		user.setAge(2);
		user.setEmail("test_02@qq.com");

		// 调用方法实现修改
		Query query = new Query(Criteria.where("_id").is(user.getId()));
		Update update = new Update();
		update.set("name", user.getName());
		update.set("age", user.getAge());
		update.set("email", user.getEmail());
		// 调用mongoTemplate的修改方法实现修改
		UpdateResult upsert = mongoTemplate.upsert(query, update, User.class);
		long modifiedCount = upsert.getModifiedCount();// 获取到修改受影响的行数
		System.out.println("受影响的条数：" + modifiedCount);
	}

	// 删除条件
	@Test
	public void deleteUser() {
		Query query = new Query(Criteria.where("_id").is("60b4b3ca861699233d33f3e2"));
		DeleteResult remove = mongoTemplate.remove(query, User.class);
		long deletedCount = remove.getDeletedCount();
		System.out.println("删除的条数：" + deletedCount);
	}

}
