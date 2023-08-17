package L12反射.C3反射Field;

import java.lang.reflect.Field;

/*
通过反射机制访问一个Java对象的属性
      给属性赋值set
      获取属性的值get
 */
public class 通过反射机制访问对象属性3 {
    public static void main(String[] args) throws Exception{

        Class studentClass = Class.forName("L12反射.C3反射Field.Student");
        Object obj = studentClass.newInstance(); //obj就是student对象，(底层调用无参构造方法)

        //获取name属性
        Field nameField = studentClass.getDeclaredField("name");
        //给obj对象(Student对象)的name属性赋值。
        nameField.set(obj,"游诗成");
        //读取属性的值
        System.out.println(nameField.get(obj));

        System.out.println("=========================================================================================");

        Field noField = studentClass.getDeclaredField("no");
        //打破封装(反射机制的缺点：打破封装，可能会不安全)
        //这样设置完之后，在外部也是可以访问private的
        noField.setAccessible(true);
        //给no属性赋值。
        noField.set(obj,11);
        //读取属性的值
        System.out.println(noField.get(obj));
    }
}
