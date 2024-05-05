package com.example.domain;

/**
 * @author 游家纨绔
 */
public class Student {
    private String name;
    private int age;
    // 声明一个引用类型
    private School school;

    // 自定义一个方法
    public void setEmail(String email){
        System.out.println("setEmail=" + email);
    }

    // Student的无参构造方法
    public Student() {
        System.out.println("调用Student的无参构造方法");
    }

    // Student的有参构造方法
    public Student(String myName, int myAge, School mySchool) {
        this.name = myName;
        this.age = myAge;
        this.school = mySchool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }
}
