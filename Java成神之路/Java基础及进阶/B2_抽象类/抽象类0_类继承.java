package B2_抽象类;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-22 14:15
 * @apiNote TODO 类继承
 */
/*
 面向对象三大特性为：封装，继承，多态
 继承的三个特点:
             1、Java语言是单继承的，一个类的直接父类只能有唯一一个
             2、Java语言可以多级继承
             3、一个子类的直接父类是唯一的，但是一个父类可以拥有多个子类

 多态：就是指一个引用（类型）在不同的情况下的多种状态。
      也可以理解为，多态是指通过指向父类的指针，来调用在不同子类中实现的方法。
 */
class Person {  // 正常的Java类
    static {
        System.out.println("这里是静态代码块");
    }
    {
        System.out.println("这里是构造代码块");
    }
    public final void finalMethod() {
        // 这是一个 final 方法，子类将无法重写它
    }
    public void methodToBeOverridden() {
        // 这是一个可被重写的方法
    }
}
class Child extends Person {
    @Override
    public void methodToBeOverridden() {
        // 这是重写父类中可被重写的方法的实现
    }
}


public class 抽象类0_类继承 {

}
