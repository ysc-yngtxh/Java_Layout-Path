package com.example.mybatis1.parser;

public class ParameterMapping {

    // 这个String对象，表示的是占位符 #{} 中的参数名
    private String property;

    public ParameterMapping(String property) {
        this.property = property;
    }

    public String getProperty() {
        return this.property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "ParameterMapping{" +
                "property='" + property + '\'' +
                '}';
    }
}
