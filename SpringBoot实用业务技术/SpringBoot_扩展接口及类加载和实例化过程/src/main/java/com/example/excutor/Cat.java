package com.example.excutor;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-13 14:41
 * @apiNote TODO
 */
public class Cat {
    {
        // 构造(普通)代码块
        System.out.println("构造(普通)代码块......");
    }

    // 普通属性的初始化
    private String name = getCatName();

    // 静态属性的初始化
    private static String color = getCatAge();

    public Cat(){
        System.out.println("构造方法==========");
    }

    static {
        // 静态代码块
        System.out.println("静态代码块......");
    }

    private String getCatName() {
        System.out.println("对普通属性的初始化");
        return "大白菜";
    }

    private static String getCatAge() {
        System.out.println("对静态属性的初始化");
        return "白色";
    }
}
