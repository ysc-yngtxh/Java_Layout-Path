package com.example.service;

import com.example.entity.Timetable;

/**
 * (Timetable)表服务接口
 *
 * @author 游家纨绔
 * @since 2024-06-30 19:04:25
 */
public interface TimetableService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Timetable queryById(Integer id);
}
