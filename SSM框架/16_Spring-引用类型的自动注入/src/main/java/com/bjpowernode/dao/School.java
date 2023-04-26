package com.bjpowernode.dao;

public class School {

    private String name;
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
