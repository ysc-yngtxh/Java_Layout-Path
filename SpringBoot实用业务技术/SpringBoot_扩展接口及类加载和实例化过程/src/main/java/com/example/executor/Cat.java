package com.example.executor;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-13 14:41
 * @apiNote TODO
 */
public class Cat {

    // TODO 构造(普通)代码块 和 普通属性 的初始化执行优先级一样，谁书写在前谁先执行。
    {
        // 构造(普通)代码块
        System.out.println("父类Cat构造(普通)代码块......");
    }

    // 普通属性的初始化
    private String name = getCatName();

    // TODO 静态代码块 和 静态属性 的初始化执行优先级一样，谁书写在前谁先执行。
    static {
        // 静态代码块
        System.out.println("父类Cat静态代码块......");
    }

    // 静态属性的初始化
    private static String color = getCatAge();

    public Cat(){
        System.out.println("父类Cat构造方法==========");
    }

    private String getCatName() {
        System.out.println("父类Cat普通属性的初始化");
        return "大白菜";
    }

    private static String getCatAge() {
        System.out.println("父类Cat静态属性的初始化");
        return "白色";
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
