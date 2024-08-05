package com.example.service;

import com.example.entity.TbUser;
import java.util.List;

/**
 * 用户表(TbUser)表服务接口
 *
 * @author 游家纨绔
 * @since 2024-08-03 16:25:15
 */
public interface TbUserService {

    List<TbUser> queryByTbUser(TbUser tbUser);

}
