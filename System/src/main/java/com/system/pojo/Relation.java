package com.system.pojo;

import java.util.Date;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/9 17:18
 */
//老师、学生和课程的关系表
public class Relation {
    private Integer id;//主键
    private Integer objectId;//对象id
    private Integer code;//课程id
    private String tenant;//区别老师和学生
    private Short deleteFlag;//逻辑删除字段
    private Date startTime;//起始时间
    private Date deadline;//截至时间

    public Relation() {
        System.out.println("调用Relation关系表的无参构造方法");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", code=" + code +
                ", tenant='" + tenant + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", startTime=" + startTime +
                ", deadline=" + deadline +
                '}';
    }
}
