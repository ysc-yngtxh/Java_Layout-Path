package com.example.excutor;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-13 14:41
 * @apiNote TODO
 */
public class Cat {

    // TODO 构造(普通)代码块 和 普通属性 的初始化执行优先级一样，谁书写在前谁先执行。
    {
        // 构造(普通)代码块
        System.out.println("Cat父类构造(普通)代码块......");
    }

    // 普通属性的初始化
    private String name = getCatName();

    // TODO 静态代码块 和 静态属性 的初始化执行优先级一样，谁书写在前谁先执行。
    static {
        // 静态代码块
        System.out.println("Cat父类静态代码块......");
    }

    // 静态属性的初始化
    private static String color = getCatAge();

    public Cat(){
        System.out.println("Cat父类构造方法==========");
    }

    private String getCatName() {
        System.out.println("Cat父类普通属性的初始化");
        return "大白菜";
    }

    private static String getCatAge() {
        System.out.println("Cat父类静态属性的初始化");
        return "白色";
    }
}
