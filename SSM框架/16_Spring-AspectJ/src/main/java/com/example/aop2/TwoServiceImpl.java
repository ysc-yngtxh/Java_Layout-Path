package com.example.aop2;

/**
 * @author 游家纨绔
 */
public class TwoServiceImpl implements TwoService {

    @Override
    public String doOther(String name, Integer age) {
        System.out.println("====目标方法doOther()====");
        return "abcd";
    }

    @Override
    public Student student(String name, Integer age) {
        System.out.println("====目标方法student()====");
        Student st = new Student();
        st.setName("小曹");
        st.setAge(1314);
        return st;
    }
}
