package com.system.service;

import com.system.pojo.Timetable;

import java.util.List;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/14 14:39
 */
public interface TimetableService {

    List<Timetable> findAll(int objectId);
}
