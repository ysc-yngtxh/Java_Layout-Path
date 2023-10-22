package M13_反射.反射Ⅱ_反射Field;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class 反射8_反编译Field {
    public static void main(String[] args) throws Exception{
        // 创建这个是为了拼接字符串
        StringBuilder s = new StringBuilder();

        Class<?> studentClass = Class.forName("M13_反射.反射Ⅱ_反射Field.Student");
        // Class<?> studentClass = Class.forName("java.util.Date");
        // Class<?> studentClass = Class.forName("java.lang.String");
        // Class<?> studentClass = Class.forName("java.lang.Thread");

        s.append(Modifier.toString(studentClass.getModifiers()) + " class " + studentClass.getSimpleName() + " {\n");

        Field[] fields = studentClass.getDeclaredFields();
        for (Field field: fields) {
            s.append("\t");  //  "\t"是制表符，即每个类下代码前的空格
            s.append(Modifier.toString(field.getModifiers()));
            s.append(" ");
            s.append(field.getType().getName());
            s.append(" ");
            s.append(field.getName());
            s.append(";\n");
        }
        s.append("}");
        System.out.println(s);
    }
}