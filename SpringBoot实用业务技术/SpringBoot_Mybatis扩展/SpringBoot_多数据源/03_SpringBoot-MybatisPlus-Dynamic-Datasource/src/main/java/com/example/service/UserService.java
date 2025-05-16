package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.User;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author 游家纨绔
 * @since 2023-09-02 22:20:00
 */
public interface UserService extends IService<User> {

	List<User> findByMasterIds();

	List<User> findSlave_1ByIds();
}
