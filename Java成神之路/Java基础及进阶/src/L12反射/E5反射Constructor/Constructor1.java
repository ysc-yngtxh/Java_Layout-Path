package L12反射.E5反射Constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class Constructor1 {
    public static void main(String[] args) throws Exception{

        StringBuilder s = new StringBuilder();
        Class vipClass =Class.forName("L12反射.E5反射Constructor.Vip");

        s.append(Modifier.toString(vipClass.getModifiers()) + " class " + vipClass.getSimpleName() + " {\n");

        Constructor[] constructors = vipClass.getDeclaredConstructors();
        for (Constructor constructor: constructors
        ) {
            s.append("\t");  //  "\t"是制表符，即每个类下代码前的空格
            s.append(Modifier.toString(constructor.getModifiers()) + " ");
            s.append(vipClass.getSimpleName() + "(");

            //参数列表
            Class[] parameterTypes = constructor.getParameterTypes();
            for (Class parameterType1:parameterTypes
            ) {
                s.append(parameterType1.getSimpleName() + ",");
            }
            //删除指定下标位置上的字符(删除多余的逗号,)
            if (parameterTypes.length > 0) {
                s.deleteCharAt(s.length() - 1);
            }

            s.append(") {}\n");
        }
        s.append("}");
        System.out.println(s);

    }
}
