package A1_抽象类;
/*
 * 接口
 * 1、接口也是一种“引用数据类型”，编译之后也是一个class字节码文件
 * 2、接口是完全抽象的（抽象类是半抽象）或者也可以说接口是特殊的抽象类。
 * 3、接口语法：
 *    interface 接口名{
 *    }
 * 4、接口中只包含两部分内容，一部分是：常量。一部分是：抽象方法
 * 5、接口中的所有元素都是public修饰的（都是公开的）
 * 6、接口中的抽象方法定义时：public abstract修饰符可以省略
 * 7、接口中的方法都是抽象方法，所以接口中的方法不能有方法体
 *
 * 接口语法：
 *    1、类和类之间叫做继承，类和接口之间叫做实现。（可以将实现看作是继承）
 *        继承使用extends关键字完成
 *        实现使用implements关键字完成
 *    2、当一个非抽象类实现接口的话，必须将接口中的所有的抽象方法全部实现（覆盖，重写）
 */


interface K{
    void m1();
}
interface M{
    void m2();
}
class C implements K,M{    // 一个类可以实现多个接口，Java不存在多继承，但是可以通过接口interface进行多实现。弥补了Java单继承的缺陷。
    public void m1(){

    }
    public void m2(){

    }
}

interface MyMath{

    // 常量
    double PI=3.1415926; // public static final double PI=3.1415926;中的public static final在接口中可以省略

    // 抽象方法
    int sum(int a,int b);  // public abstract int sum(int a,int b);中的public abstract 在接口中也可以省略
    //接口当中的抽象方法不可以有方法体
    int sub(int a,int b);
}
/*
  abstract class MyMathImpl1 implements MyMath{
  因为MyMathImpl1加上了abstract关键字变成了抽象类，所以继承MyMath类不需要覆盖/重写其父类的方法。
}*/
class MyMathImpl2 implements MyMath{
    public int sum(int a,int b){   // 实现访问父类方法的权限只能更高，不能更低。所以父类中的方法都是public公开的，子类中只能加上public.用以符合语法
        return a + b;              // private < 缺省 < protected < public
    }
    public int sub(int a,int b){
        return a-b;
    }
}


class Abc{
}
interface Flyable{
    void fly();
}
class pig extends Abc implements Flyable{
    public void fly(){
        System.out.println("先谋生再谋爱！");
    }
}
public class 抽象类_3_接口类 {
    public static void main(String[] args){
        System.out.println(MyMath.PI);     // 直接调用接口当中的常量，不需要new对象。

        MyMath m=new MyMathImpl2();
        System.out.println( m.sum(59,12) );

        Flyable f=new pig();
        f.fly();
    }

}

