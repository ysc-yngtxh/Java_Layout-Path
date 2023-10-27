package com.example.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myTeacher")
public class Teacher {

    @Value("敏敏妹妹")
    private String name;
    @Value("2020915")
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
