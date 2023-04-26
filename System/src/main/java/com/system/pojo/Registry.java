package com.system.pojo;

import java.util.Date;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/7 15:54
 */
//注册登录
public class Registry {
    private Integer id;//主键id
    private String username;//用户名
    private String password;//密码
    private String tenant;//租户用来区别老师和学生
    private Short deleteFlag;//逻辑删除字段   0 表示未删除， 1 表示删除
    private Date dateTime;//注册时间
    private Date updateTime;//更新时间

    public Registry() {
        System.out.println("调用Registry登录注册的无参构造方法");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Registry{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tenant='" + tenant + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", dateTime=" + dateTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
