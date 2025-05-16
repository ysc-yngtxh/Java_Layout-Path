package com.example.service;

import com.example.domain.Student;
import com.example.mapper.StudentMapper;
import java.util.concurrent.TimeUnit;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 */
@Component  // 这个注解是交给Spring容器管理
@DubboService(interfaceName = "com.example.service.StudentService", version = "1.0.0", group = "dev", timeout = 15000)
// 这个注解是暴露接口服务的
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	public Student queryStudentById(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer queryAllStudentCount() {
		// 提升系统性能，用户体验提升
		// 首先去redis缓存中查询，如果有:直接使用;  如果没有:去数据库查询并存放在redis缓存中
		Integer allStudentCount = (Integer) redisTemplate.opsForValue().get("allStudentCount");

		// 判断是否有值
		if (allStudentCount == null) {
			// 去数据库查询
			allStudentCount = studentMapper.selectAllStudentCount();
			// 并存放到redis缓存中。TimeUnit.SECONDS表示的是时间单位：秒
			redisTemplate.opsForValue().set("allStudentCount", allStudentCount, 30, TimeUnit.SECONDS);
		}
		return allStudentCount;
	}
}
