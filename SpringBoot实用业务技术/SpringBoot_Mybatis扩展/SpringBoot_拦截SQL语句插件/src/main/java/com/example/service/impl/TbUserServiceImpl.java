package com.example.service.impl;

import com.example.entity.TbUser;
import com.example.mapper.TbUserDao;
import com.example.service.TbUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * (TbUser)表服务实现类
 *
 * @author makejava
 * @since 2023-08-25 00:19:30
 */
@Service("tbUserService")
public class TbUserServiceImpl implements TbUserService {
    @Resource
    private TbUserDao tbUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbUser queryById(Long id) {
        return this.tbUserDao.queryById(id);
    }

    /**
     * 通过ID查询单条数据,忽略 tenant_id 作为查询条件
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbUser queryByIdIgnoreTenant(Long id) {
        return this.tbUserDao.queryByIdIgnoreTenant(id);
    }

    @Override
    public Map<String, TbUser> query() {
        return this.tbUserDao.query();
    }

    /**
     * 新增数据
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    @Override
    public TbUser insert(TbUser tbUser) {
        this.tbUserDao.insert(tbUser);
        return tbUser;
    }

    /**
     * 修改数据
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    @Override
    public TbUser update(TbUser tbUser) {
        this.tbUserDao.update(tbUser);
        return this.queryById(tbUser.getId());
    }

    /**
     * 修改数据
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    @Override
    public TbUser fullTableUpdate(TbUser tbUser) {
        this.tbUserDao.fullTableUpdate(tbUser);
        return this.queryById(tbUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tbUserDao.deleteById(id) > 0;
    }
}
