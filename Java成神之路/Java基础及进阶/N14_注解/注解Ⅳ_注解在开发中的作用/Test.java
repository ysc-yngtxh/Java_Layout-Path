package N14_注解.注解Ⅳ_注解在开发中的作用;

import java.lang.reflect.Field;

/*
  需求：
      假设有这样一个注解，叫做：@Id
      这个注解只能出现在类上面，当这个类中有这个注解的时候，要求这个类中必须有一个int类型的id属性.
      如果没有这个属性就报异常，如果有这个属性则正常执行！
 */
public class Test {
    public static void main(String[] args) throws Exception{
        Class<?> userClass = Class.forName("N14_注解.注解Ⅳ_注解在开发中的作用.User");
        // 判断类上是否存在Id注解
        if(userClass.isAnnotationPresent(Id.class)){
            // 当一个类上面有@Id注解的时候，要求类中必须存在int类型的id属性
            // 如果没有int类型的id属性则报异常
            // 获取类的属性
            Field[] fields = userClass.getDeclaredFields();
            boolean isOK = false;   // 给一个默认的标记
            for (Field field : fields) {
                if("id".equals(field.getName()) && "int".equals(field.getType().getSimpleName())){
                    // 表示这个类是合法的类。有@Id注解，则这个类中必须有int类型的id
                    isOK = true;  // 表示合法
                    break;
                }
            }

            // 判断是否合法
            if(!isOK){
                throw new HasNotIdPropertyException("被@Id注解标注的类中必须要有一个int类型的id属性");
            }
        }
    }
}
