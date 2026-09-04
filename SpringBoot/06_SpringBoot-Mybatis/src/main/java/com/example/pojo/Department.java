package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 院系表 实体类
 * 对应数据库表：db_department
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    /**
     * 部门唯一标识ID（主键，自增）
     */
    private Integer id;

    /**
     * 部门名称（非空）
     */
    private String name;

    /**
     * 部门描述（可为空）
     */
    private String description;

    /**
     * 部门是否存在：0-存在，1-删除（默认0）
     */
    private Integer delFlag;

    /**
     * 部门创建时间（可为空）
     */
    private LocalDateTime createTime;

    /**
     * 部门最后更新时间（可为空）
     */
    private LocalDateTime updateTime;

    private transient List<Student> studentList;
}
