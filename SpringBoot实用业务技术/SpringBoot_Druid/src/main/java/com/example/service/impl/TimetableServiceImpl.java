package com.example.service.impl;

import com.example.entity.Timetable;
import com.example.mapper.TimetableMapper;
import com.example.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Timetable)表服务实现类
 *
 * @author 游家纨绔
 * @since 2024-06-30 19:04:25
 */
@Service
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    private TimetableMapper timetableMapper;

    /**
     * 通过ID查询单条数据
     */
    @Override
    public Timetable queryById(Integer id) {
        return timetableMapper.queryById(id);
    }
}
