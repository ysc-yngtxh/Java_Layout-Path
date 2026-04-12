package com.example.repository;

import com.example.pojo.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author 游家纨绔
 * @Description TODO 注意：该 Repository 使用的是 User Dto，而 User 中的文档字段有设置分词器。
 *                        当连接 elasticsearch 时，如果服务端的 ES 不存在指定的分词器，会导致连接失败。
 * @Date 2025-03-05 10:55:00
 */
@Repository
public interface ESRepository extends ElasticsearchRepository<User, String> {

	/**
	 * 根据年龄统计用户数量
	 * @param age 年龄
	 * @return 用户数量
	 */
	long countUsersByAge(Integer age);

}
