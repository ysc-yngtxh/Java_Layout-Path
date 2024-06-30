package com.example.mapper;

import com.example.entity.Timetable;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Timetable)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2024-06-30 19:04:25
 */
public interface TimetableMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Timetable queryById(Integer id);

    /**
     * 统计总行数
     *
     * @param timetable 查询条件
     * @return 总行数
     */
    long count(Timetable timetable);

    /**
     * 新增数据
     *
     * @param timetable 实例对象
     * @return 影响行数
     */
    int insert(Timetable timetable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Timetable> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Timetable> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Timetable> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Timetable> entities);

    /**
     * 修改数据
     *
     * @param timetable 实例对象
     * @return 影响行数
     */
    int update(Timetable timetable);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

