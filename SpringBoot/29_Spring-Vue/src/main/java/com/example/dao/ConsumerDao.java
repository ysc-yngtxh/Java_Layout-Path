package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Consumer;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Consumer)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-09 09:16:49
 */
public interface ConsumerDao extends BaseMapper<Consumer> {

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Consumer> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Consumer> entities);

}

