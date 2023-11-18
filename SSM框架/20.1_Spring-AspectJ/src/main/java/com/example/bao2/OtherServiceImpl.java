package com.example.bao2;

/**
 * @author 游家纨绔
 */
public class OtherServiceImpl implements OtherService {
    @Override
    public String doOther(String name, Integer age) {
        System.out.println("====目标方法doOther()====");
        return "abcd";
    }

    @Override
    public Student student(String name, Integer age) {
        System.out.println("====目标方法student()====");
        Student st = new Student();
        st.setName("李晶晶");
        st.setAge(1314);
        return st;
    }
}
