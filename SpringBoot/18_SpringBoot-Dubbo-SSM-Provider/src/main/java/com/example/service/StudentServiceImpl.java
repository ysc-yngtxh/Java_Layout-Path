package com.example.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.mapper.StudentMapper;
import com.example.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 游家纨绔
 */
@Component  // 这个注解是交给spring容器管理
@Service(interfaceName="com.example.service.StudentService", version="1.0.0", timeout = 15000)
// 这个注解是暴露接口服务的
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public Student queryStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer queryAllStudentCount() {

        // 提升系统性能，用户体验提升
        // 首先去redis缓存中查询,如果有:直接使用;  如果没有:去数据库查询并存放在redis缓存中
        Integer allStudentCount = (Integer) redisTemplate.opsForValue().get("allStudentCount");

        // 判断是否有值
        if(allStudentCount == null){
            // 去数据库查询
            allStudentCount = studentMapper.selectAllStudentCount();
            // 并存放到redis缓存中
            redisTemplate.opsForValue().set("allStudentCount", allStudentCount, 30, TimeUnit.SECONDS);
            // TimeUnit.SECONDS表示的是在内存中存活时间：30秒
        }
        return allStudentCount;
    }
}
