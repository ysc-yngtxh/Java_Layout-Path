package B2_抽象类;

/*
   抽象类（abstract）
   1、类和类之间具有共同特征，将这些共同特征提取出来，形成的就是抽象类。
      类本身是不存在的，所以抽象类无法创建对象《无法实例化》
   2、抽象类也属于引用类型
   3、抽象类怎么定义？
      abstract class 类名{
      }

   4、抽象类是无法实例化，无法创建对象的，所以抽象类是用来被子类继承的

   5、不可以在抽象类中加上关键字final，因为final类不能被继承。

   6、抽象类的子类也可以是抽象类

   7、抽象类虽然无法实例，但是有构造方法，构造方法是供子类使用的

   8、抽象类关联到一个概念：抽象方法
         抽象方法表示没有实现的方法，没有方法体的方法。例如：public abstract void doSome();
         抽象方法特点是：
                       1️⃣、没有方法体，以分号结尾
                       2️⃣、前面修饰符列表中有abstract关键字

   9、问题：为什么抽象类不能实例化却有构造器？
          抽象类是可以被继承的，那么其子类在实例化后怎么去调用父类的变量或者方法呢？
          就只能通过父类的构造器，通过 super 关键字进行调用。
          所以抽象类必须要有构造器，而抽象类的构造器通常用于初始化抽象类中的成员变量，不做实例化使用。

   10、总结：抽象类具备正常Java类的所有的能力，只是不能实例化，并且多了一个没有方法体的抽象方法给继承类去重写。
            抽象类中的非抽象方法(正常方法)不用重写，其他方法(即抽象方法)必须重写。
 */
abstract class A {
    // 这个是构造方法，不是方法体
    A() {
        System.out.println("A,无参构造函数");
    }
}
class B extends A{
    B() {
        System.out.println("A,无参构造函数");
    }
}


abstract class Animal {              // 动物类
    private int data;
    public Animal(int data) {
        this.data = data;
    }

    // 抽象方法
    public abstract void move();

    // 正常方法体
    public void abc() {
        System.out.println("ff");
    };
}
class Bird extends Animal {          // 小鸟类
    public Bird(int data) {          // 父类没有无参构造器只有有参构造器，因此子类必须通过 super 赋值 data 值，完成父类构造
        super(data);
    }
    public void move() {             // 必须要将父类的抽象方法进行方法覆盖
        System.out.println("鸟儿在飞翔");
    }
}
abstract class Cat extends Animal {  // 小猫类
    public Cat(int data) {           // 父类没有无参构造器只有有参构造器，因此子类必须通过 super 赋值 data 值，完成父类构造
        super(data);
    }
    public void move() {             // 必须要将父类的抽象方法进行方法覆盖
        System.out.println("猫在吃鱼");
    }
}

class Master extends Animal {
    public Master(int data) {
        super(data);
    }
    public void move() {             // 必须要将父类的抽象方法进行方法覆盖
        System.out.println("小曹爱吃肉");
    }
}

public class 抽象类1_抽象类 {
    public static void feed(Animal an) {
        an.move();
    }
    
    // 多态：就是指一个引用（类型）在不同的情况下的多种状态。
    //      也可以理解为，多态是指通过指向父类的指针，来调用在不同子类中实现的方法。
    public static void main(String[] args) {
        Animal a = new Bird(10);   // 多态：父类引用指向子类对象
        a.move();
        a.abc();
        // Animal a = new Cat(); // 为什么无法创建Cat对象，记住：抽象类不能创建对象。

        // 这个时候多态的功能就体现出来了，只需要定义一个函数就可以传入不同的对象，调用相同方法的不同实现
        feed(new Bird(20));
        feed(new Master(30));
    }
}
