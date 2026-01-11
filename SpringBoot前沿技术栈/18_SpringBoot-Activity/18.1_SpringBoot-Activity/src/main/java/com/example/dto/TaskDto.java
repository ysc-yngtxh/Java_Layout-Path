package com.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.activiti.engine.task.Task;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author 游家纨绔
 * @dateTime 2026-01-10 22:00
 * @apiNote TODO 任务DTO类
 */
@Data
@NoArgsConstructor
public class TaskDto {

    private String taskId;
    private String name;
    private String assignee;
    private String owner;
    private LocalDateTime createTime;

    // 从 Task实体 转换为 DTO的构造器
    public TaskDto(Task task) {
        this.taskId = task.getId();
        this.name = task.getName();
        this.assignee = task.getAssignee();
        this.owner = task.getOwner();
        this.createTime = task.getCreateTime().toInstant().atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime();
    }

}
