package com.example.service.impl;

import com.example.entity.TbUser;
import com.example.mapper.TbUserMapper;
import com.example.service.TbUserService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 用户表(TbUser)表服务实现类
 *
 * @author 游家纨绔
 * @since 2024-08-03 16:25:15
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public List<TbUser> queryByTbUser(TbUser tbUser) {
        return this.tbUserMapper.queryByTbUser(tbUser);
    }
}
