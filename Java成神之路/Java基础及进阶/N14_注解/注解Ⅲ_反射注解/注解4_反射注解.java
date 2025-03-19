package N14_注解.注解Ⅲ_反射注解;

import java.lang.reflect.Method;

/**
  只允许该注解可以标注类，方法
 */
public class 注解4_反射注解 {
    public static void main(String[] args) throws Exception {
        // 获取这个类
        Class<?> c = Class.forName("N14_注解.注解Ⅲ_反射注解.MyAnnotationTest");
        // 判断这个类型上是否有这个@MyAnnotation注解
        if(c.isAnnotationPresent(MyAnnotation.class)) {
            // 获取该注解对象
            MyAnnotation myAnnotation = c.getAnnotation(MyAnnotation.class);
            System.out.println("类上面的注解对象" + myAnnotation);
            // 获取注解对象的属性
            myAnnotation.value();
        }

        // 判断String类上面是否存在这个注解
        Class<?> stringClass = Class.forName("java.lang.String");
        System.out.println(stringClass.isAnnotationPresent(MyAnnotation.class));  // false


        // 通过反射机制获取注解对象属性的值
        // 获取doSome()方法
        Method doSomeMethod = c.getDeclaredMethod("doSome");
        // 判断该方法上是否存在这个注解
        if(doSomeMethod.isAnnotationPresent(MyAnnotation.class)) {
            // 获取类注解对象要转型而获取方法对象则不需要转型
            MyAnnotation myAnnotation = doSomeMethod.getAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation.username());
            System.out.println(myAnnotation.password());
        }
    }
}
