package com.example.dao;

import com.example.annotation.IgnoreTenantId;
import com.example.entity.TbUser;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (TbUser)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2023-08-25 00:19:30
 */
@Mapper
public interface TbUserDao {

    /**
     * 通过ID查询单条数据,忽略 tenant_id 作为查询条件
     *
     * @param id 主键
     * @return 实例对象
     */
    TbUser queryById(Long id);

    /**
     * 通过ID查询单条数据,忽略 tenant_id 作为查询条件
     *
     * @param id 主键
     * @return 实例对象
     */
    @IgnoreTenantId
    TbUser queryByIdIgnoreTenant(Long id);

    @MapKey("userName")
    Map<String, TbUser> query();

    /**
     * 统计总行数
     *
     * @param tbUser 查询条件
     * @return 总行数
     */
    long count(TbUser tbUser);

    /**
     * 新增数据
     *
     * @param tbUser 实例对象
     * @return 影响行数
     */
    int insert(TbUser tbUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TbUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TbUser> entities);

    /**
     * 修改数据
     *
     * @param tbUser 实例对象
     * @return 影响行数
     */
    int update(TbUser tbUser);

    /**
     * 修改数据
     *
     * @param tbUser 实例对象
     * @return 影响行数
     */
    int fullTableUpdate(TbUser tbUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

