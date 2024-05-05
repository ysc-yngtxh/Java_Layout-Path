package M13_反射.反射Ⅲ_反射Method;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/*
  必须掌握。重要程度五颗星*****
    反射机制，让代码很具有通用性，可变化的内容都是写到配置文件当中，
    将来修改配置文件之后，创建的对象不一样了，调用的方法也不同了。
    但是Java代码不需要做任何改动，这就是反射机制的魅力。
 */
public class 反射12_反射机制调用方法 {
    public static void main(String[] args) throws Exception{
        // 不使用反射机制调用方法
        User users= new User();
        boolean logins = users.login("admin", "123");
        System.out.println(logins ? "登陆成功" : "登陆失败");

        // 使用反射机制调用方法
        Class<?> userser = Class.forName("M13_反射.反射Ⅲ_反射Method.User"); // 获取class
        Object obj = userser.newInstance();   // 创建对象，底层调用的是无参构造方法
        Method loginMethod = userser.getDeclaredMethod("login", String.class, String.class); // 获取Method
        Object retValue = loginMethod.invoke(obj, "admin", "123"); // invoke英文翻译是调用,即调用方法
        System.out.println((boolean)retValue ? "登陆成功" : "登陆失败");

        // 通过判断返回值是否泛型，来决定执行逻辑
        Class resultType = null;
        Method[] declaredMethods = userser.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            Type genericReturnType = declaredMethods[i].getGenericReturnType();
            if (genericReturnType instanceof Class) {
                // 表示返回值不是泛型
                resultType = (Class) genericReturnType;
            } else if (genericReturnType instanceof ParameterizedType) {
                // 表示返回值是泛型
                Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                // 这里获取的是 Map<String, List<Integer>> 里的泛型String。
                resultType = (Class) actualTypeArguments[0];
            }
            System.out.println(resultType.getSimpleName());
        }
    }
}
