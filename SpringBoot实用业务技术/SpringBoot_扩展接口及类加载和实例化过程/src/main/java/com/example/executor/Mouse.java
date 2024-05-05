package com.example.executor;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-13 22:13
 * @apiNote TODO
 */
public class Mouse extends Cat{
    // TODO 构造(普通)代码块 和 普通属性 的初始化执行优先级一样，谁书写在前谁先执行。
    {
        // 构造(普通)代码块
        System.out.println("Mouse本类构造(普通)代码块......");
    }

    // 普通属性的初始化
    private String names = getMouseName();

    // TODO 静态代码块 和 静态属性 的初始化执行优先级一样，谁书写在前谁先执行。
    static {
        // 静态代码块
        System.out.println("Mouse本类静态代码块......");
    }

    // 静态属性的初始化
    private static String colors = getMouseAge();

    public Mouse(){
        System.out.println("Mouse本类构造方法==========");
    }

    private String getMouseName() {
        System.out.println("Mouse本类普通属性的初始化");
        return "大白菜";
    }

    private static String getMouseAge() {
        System.out.println("Mouse本类静态属性的初始化");
        return "白色";
    }
}
