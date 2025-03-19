package M13_反射.反射Ⅳ_反射Constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class 反射13_Constructor {
    public static void main(String[] args) throws Exception {
        StringBuilder s = new StringBuilder();
        Class<?> vipClass =Class.forName("M13_反射.反射Ⅳ_反射Constructor.Vip");

        s.append( Modifier.toString(vipClass.getModifiers()) )
                .append(" class ")
                .append(vipClass.getSimpleName())
                .append(" {\n");

        Constructor<?>[] constructors = vipClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            s.append("\t");  // "\t"是制表符，即每个类下代码前的空格
            s.append(Modifier.toString(constructor.getModifiers())).append(" ");
            s.append(vipClass.getSimpleName()).append("(");

            // 参数列表
            Parameter[] parameters = constructor.getParameters();
            // 参数类型列表
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < parameters.length; i++) {
                if (i != 0) {
                    s.append(" ");
                }
                // 第一种写法：分别使用参数类型和参数名称拼接
                s.append(parameterTypes[i].getSimpleName() + " ")
                 .append(parameters[i].getName())
                 .append(",");
                // 第二种写法：parameters[i] 直接包含了修饰符和参数名
                // s.append(parameters[i]).append(",");
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
