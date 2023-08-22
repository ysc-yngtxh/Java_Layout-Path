package L12_反射.D4反射Method;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Method2 {
    public static void main(String[] args) throws Exception{

        // 获取类
        Class<?> userClass = Class.forName("L12_反射.D4反射Method.user");

        // 获取所有的Method(包括私有的)
        Method[] methods = userClass.getDeclaredMethods();

        // 遍历
        for (Method method:methods) {
            // 获取修饰符列表
            System.out.println(Modifier.toString(method.getModifiers()));
            // 获取方法的返回值类型
            System.out.println(method.getReturnType().getSimpleName());
            // 获取方法名
            System.out.println(method.getName());
            // 获取方法参数列表（一个方法可能有多个形参）
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> parameterType:parameterTypes) {
                System.out.println(parameterType.getSimpleName());
            }
        }

        System.out.println("=========================================================================================");

        // 反编译Method

        StringBuilder s = new StringBuilder(); // 拼接字符串

        s.append(Modifier.toString(userClass.getModifiers()) + " class " + userClass.getSimpleName() + " {\n");


        for (Method method1:methods
        ) {
            s.append("\t");  //  "\t"是制表符，即每个类下代码前的空格
            s.append(Modifier.toString(method1.getModifiers()) + " ");
            s.append(method1.getReturnType().getName() + " ");
            s.append(method1.getName() + "(");

            // 参数列表
            Class[] parameterTypes1 = method1.getParameterTypes();
            if (parameterTypes1.length != 0) {
                for (Class parameterType2:parameterTypes1
                ) {
                    s.append(parameterType2.getSimpleName() + ",");
                }
                // 删除指定下标位置上的字符(删除多余的逗号,)
                s.deleteCharAt(s.length()-1);
            }

            s.append(") {}\n");
        }
        s.append("}");
        System.out.println(s);


    }
}
