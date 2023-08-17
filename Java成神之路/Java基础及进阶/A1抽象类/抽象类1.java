package A1抽象类;
/*
 * A1抽象类（abstract）
 * 1、类和类之间具有共同特征，将这些共同特征提取出来，形成的就是抽象类。
 *    类本身是不存在的，所以抽象类无法创建对象《无法实例化》
 * 2、抽象类也属于引用类型
 * 3、抽象类怎么定义？
 *    abstract class 类名{
 *    }
 *
 * 4、抽象类是无法实例化，无法创建对象的，所以抽象类是用来被子类继承的
 *    因为抽象类中只有常量和抽象方法，但没有方法体。所以抽象类创建自己的对象是没有意义的
 *
 * 5、不可以在抽象类中加上关键字final，因为final类不能被继承。
 *
 * 6、抽象类的子类可以是抽象类
 *
 * 7、抽象类虽然无法实例，但是有构造方法，构造方法是供子类使用的
 *
 * 8、抽象类关联到一个概念：抽象方法
 *            抽象方法表示没有实现的方法，没有方法体的方法。例如：public abstract void doSome();
 *            抽象方法特点是：
 *                           1、没有方法体，以分号结尾
 *                           2、前面修饰符列表中有abstract关键字
 *
 *   总结一下：抽象类相当于是被继承类的父类，不提供实例化的能力(如果能实例化那我干嘛还要一个继承的抽象类？直接自己实现方法不是更好吗？)
 *           可以这么理解：抽象类具备正常类的所有的能力，只是不能实例化，多了一个没有方法体的抽象方法给被继承类去重写。
 *                      抽象类中的非抽象方法不用重写，其他方法(即抽象方法)必须重写
 */

abstract class A{
    A(){
        System.out.println("A,无参构造函数");
    }  //这个是构造方法，不是方法体
}
class B extends A{
    B(){
        System.out.println("A,无参构造函数");
    }
}

abstract class Animal{
    private int qwer;
    public Animal() {
    }

    public Animal(int qwer) {
        this.qwer = qwer;
    }

    //动物类
    public abstract void move();

    public void abc(){
        System.out.println("ff");
    };
}
class Bird extends Animal{      //鸟儿类
    public void move(){                          //必须要将父类的抽象方法进行方法覆盖，否则Bird继承Animal的构造体中肯定有public abstract void move();
        System.out.println("鸟儿在飞翔");  //但是有abstract的抽象方法必是抽象类，但Bird类不是抽象类，因此在编译时会不通过，所以要进行方法覆盖
    }
}
abstract class Cat extends Animal{
    public void move(){
        System.out.println("猫在吃鱼");
    }
}

public class 抽象类1 {
    public static void main(String[] args) {
        Animal a=new Bird();
        a.move();
        //Animal a=new Cat();为什么无法创建Cat对象，记住：抽象类不能创建对象。
    }
}

