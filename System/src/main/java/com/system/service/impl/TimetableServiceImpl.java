package com.system.service.impl;

import com.system.dao.TimetableDao;
import com.system.pojo.Timetable;
import com.system.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/14 14:39
 */
@Service
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    private TimetableDao timetableDao;

    //通过用户id 查找课程表
    @Override
    public List<Timetable> findAll(int objectId) {
        List<Timetable> timetable = timetableDao.selectAll(objectId);
        System.out.println(timetable);
        return timetable;
    }
}
