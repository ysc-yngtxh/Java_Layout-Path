package com.example.controller;

import com.example.entity.Timetable;
import com.example.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * (Timetable)表控制层
 *
 * @author 游家纨绔
 * @since 2024-06-30 19:04:25
 */
@RestController
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<Timetable> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(timetableService.queryById(id));
    }
}

