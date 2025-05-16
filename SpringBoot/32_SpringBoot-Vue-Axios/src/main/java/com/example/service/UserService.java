package com.example.service;

import com.example.entity.User;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author 游家纨绔
 * @since 2023-07-09 09:00:00
 */
public interface UserService {

	boolean check(String username, String password);

	List<User> queryPage(Integer page, Integer size);

	Integer countAll();
}
