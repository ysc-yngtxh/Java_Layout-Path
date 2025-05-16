package com.example.repository;

import com.example.pojo.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author 游家纨绔
 * @Description TODO
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
