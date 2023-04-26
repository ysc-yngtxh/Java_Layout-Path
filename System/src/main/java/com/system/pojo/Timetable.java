package com.system.pojo;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/9 17:29
 */
//个人课表
public class Timetable {
    private Integer id;//主键id
    private Integer objectId;//对象id
    private Integer code;//课程id
    private String tenant;//租户用来区别老师和学生
    private Integer week;//星期
    private Integer start;//课程上课时间
    private Integer finish;//下课时间
    private Short deleteFlag;//逻辑删除字段
    private Courses courses;//面向专业课程对象

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public Timetable() {
        System.out.println("调用Timetable个人课表的无参构造方法");
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

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", code=" + code +
                ", tenant='" + tenant + '\'' +
                ", week='" + week + '\'' +
                ", start='" + start + '\'' +
                ", finish='" + finish + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
