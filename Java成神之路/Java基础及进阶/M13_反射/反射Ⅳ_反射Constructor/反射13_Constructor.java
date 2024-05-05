package M13_反射.反射Ⅳ_反射Constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class 反射13_Constructor {
    public static void main(String[] args) throws Exception{
        StringBuilder s = new StringBuilder();
        Class<?> vipClass =Class.forName("M13_反射.反射Ⅳ_反射Constructor.Vip");

        s.append( Modifier.toString(vipClass.getModifiers()) )
                .append(" class ")
                .append(vipClass.getSimpleName())
                .append(" {\n");

        Constructor<?>[] constructors = vipClass.getDeclaredConstructors();
        for (Constructor<?> constructor: constructors) {
            s.append("\t");  //  "\t"是制表符，即每个类下代码前的空格
            s.append(Modifier.toString(constructor.getModifiers())).append(" ");
            s.append(vipClass.getSimpleName()).append("(");

            // 参数列表
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (Class<?> parameterType1:parameterTypes
            ) {
                s.append(parameterType1.getSimpleName()).append(",");
            }
            // 删除指定下标位置上的字符(删除多余的逗号,)
            if (parameterTypes.length > 0) {
                s.deleteCharAt(s.length() - 1);
            }
            s.append(") {}\n");
        }
        s.append("}");
        System.out.println(s);
    }
}