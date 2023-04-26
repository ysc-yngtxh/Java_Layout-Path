package com.system.pojo;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/9 17:13
 */
//面向专业的课程表
public class Courses {
    private Integer cid;//主键课程id
    private Integer code;//课程编码
    private String cname;//课程名称
    private String zyname;//专业名称
    private String teacher;//任课教师
    private String tclass;//授课班级
    private Short deleteFlag;//逻辑删除字段
    private Registry registry;//登录注册对象

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public Courses() {
        System.out.println("调用Courses面向专业课程的无参构造方法");
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getZyname() {
        return zyname;
    }

    public void setZyname(String zyname) {
        this.zyname = zyname;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTclass() {
        return tclass;
    }

    public void setTclass(String tclass) {
        this.tclass = tclass;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "cid=" + cid +
                ", code=" + code +
                ", cname='" + cname + '\'' +
                ", zyname='" + zyname + '\'' +
                ", teacher='" + teacher + '\'' +
                ", tclass='" + tclass + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
