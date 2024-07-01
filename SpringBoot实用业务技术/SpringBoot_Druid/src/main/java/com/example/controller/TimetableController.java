package com.example.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.example.entity.Timetable;
import com.example.service.TimetableService;
import java.sql.Connection;
import javax.sql.DataSource;
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
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @Autowired
    private DataSource dataSource;

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<Timetable> queryById(@PathVariable("id") Integer id) {
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println(druidDataSource.getMaxActive());
        System.out.println(druidDataSource.getInitialSize());
        System.out.println(druidDataSource.getMinIdle());

        return ResponseEntity.ok(timetableService.queryById(id));
    }

    /**
     * 返回Druid的监控数据
     */
    @GetMapping("/druid-status")
    public Object druidStat(){
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}

