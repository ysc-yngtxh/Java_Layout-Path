package O15_泛型及练习题.泛型;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-25 18:16
 * @apiNote TODO 泛型
 */
public class 泛型1_定义 {
    /**
     * 泛型通配符 "?" 和 T、E、R、K、V 对于程序运行时没有区别，只是名字不同
     *
     *     ? 表示不确定的泛型类型
     *     T (type) 表示具体的一个泛型类型
     *     K V (key value) 分别代表 Map 中的键值 Key Value
     *     E (element) 代表元素，例如 ArrayList 中的元素
     *     R (return) 代表返回值类型.
     *
     *     IPage<? extends AppOrderResponse> // 表示泛型最高类型是 AppOrderResponse，只能是它或它的子类
     *     IPage<? super AppOrderResponse>   // 表示泛型最低类型是 AppOrderResponse，只能是它或它的父类
     *
     * 泛型擦除
     *     所谓的泛型擦除其实很简单，简单来说就是泛型只在编译时起作用，运行时泛型还是被当成 Object 来处理
     * */

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        // add 方法形参类型为 String
        list1.add("XiaoCao");
        List<Integer> list2 = new ArrayList<>();
        // 实例化 ArrayList 时虽然传入不同的泛型，但其实它们仍然还是同一个类型。
        System.out.println("list1 和 list2 类型相同吗：" + (list1.getClass() == list2.getClass()));

        Method[] methods = list1.getClass().getMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.getName().equals("add")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    for (Class<?> parameterType : parameterTypes) {
                        // 按道理说打印出来应该是指定的String泛型，但是这里泛型的参数 E 运行时得到的却都是 Object 类型
                        // TODO 注意：在反射里获取方法返回值类型中，可以通过 parameterType.getName() 获取到对应的泛型值
                        //           这是因为，方法返回值是确切的泛型类型（如：Map<String, List<Integer>>）
                        //           而这个的泛型使用的是通配符（E），故而获取类型只能为 Object。
                        System.out.println("add(E e) 形参 E 的类型为：" + parameterType.getName());
                    }
                }
            } else if (method.getName().equals("get")) {
                Class<?> returnType = method.getReturnType();
                // 按道理说打印出来应该是指定的String泛型，但是这里泛型的参数 E 运行时得到的却都是 Object 类型
                System.out.println("E get(int index) 的返回值 E 的类型为：" + returnType.getName());
            }
        }

        // 以上add()、get()方法打印类型都是Object。证明泛型在编译期起作用，运行时一律被擦除当做Object看待，这就是泛型擦除。
    }
}
