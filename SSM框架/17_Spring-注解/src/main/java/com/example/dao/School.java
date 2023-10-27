package com.example.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mySchool")
public class School {

    @Value("等我变优秀哦！")
    private String name;
    @Value("最迟明年，如果你还没有对象，我就向你表白！")
    private String addrerss;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddrerss() {
        return addrerss;
    }

    public void setAddrerss(String addrerss) {
        this.addrerss = addrerss;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", addrerss='" + addrerss + '\'' +
                '}';
    }
}
