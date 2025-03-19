package M13_反射.反射Ⅱ_反射Field;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/*
 * Field翻译为字段，其实就是属性/成员
 */
public class 反射7_获取Field {
    public static void main(String[] args) throws Exception{
        // 获取整个类
        Class<?> studentClass = Class.forName("M13_反射.反射Ⅱ_反射Field.Student");

        // 获取类名
        String className = studentClass.getName();
        System.out.println("完整类名:" + className);
        String simpleName = studentClass.getSimpleName();
        System.out.println("简类名:" + simpleName);

        // 获取类中所有的public修饰的Field
        Field[] fields = studentClass.getFields(); // 只能获取公开的属性，即 public修饰 的Field
        System.out.println(fields.length);         // 测试数组中只有有2个元素
        // 取出这个Field
        Field f = fields[0];
        // 取出这个Field的名字
        System.out.println(f.getName());

        System.out.println("=========================================================================================");

        // 获取所有的Field
        Field[] fs = studentClass.getDeclaredFields();
        System.out.println(fs.length);
        // 遍历
        for (Field field : fs) {
            // 获取属性的修饰符列表
            int i = field.getModifiers(); // 返回的修饰符是一个数字，每一个数字都是修饰符的代号！！
            System.out.println(i);
            // 这里的 toString() 方法是返回描述指定修饰符中的访问修饰符标志的字符串。
            String modifierString = Modifier.toString(i);
            System.out.println(modifierString);  // 就是将代号转换成字符串
            // 获取属性的类型
            Class<?> fieldType = field.getType();
            String fName = fieldType.getName();  // 获取简单的类型名：String fName = fieldType.getSimpleName();
            System.out.println(fName);
            // 获取属性名字
            System.out.println(field.getName());
        }
    }
}
