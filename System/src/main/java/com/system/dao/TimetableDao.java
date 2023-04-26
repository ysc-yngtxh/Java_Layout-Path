package com.system.dao;

import com.system.pojo.Timetable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/14 14:38
 */
@Repository
public interface TimetableDao {
    List<Timetable> selectAll(int objectId);
}
