package com.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 * @author 游家纨绔
 * @since 2023-09-02 22:20:00
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	// 类和方法上都没有@DS 注解，就会使用默认的数据源
	public List<User> findByMasterIds() {
		List<Integer> ids = Arrays.asList(1, 3, 5, 8, 10);
		return baseMapper.selectBatchIds(ids);
	}

	@DS("slave_1")
	public List<User> findSlave_1ByIds() {
		List<Integer> ids = Arrays.asList(1, 3, 5, 8, 10);
		return baseMapper.selectBatchIds(ids);
	}
}
