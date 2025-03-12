package B2_抽象类;

/*
  接口在开发中作用：
        接口的使用离不开多态机制。接口是抽象类，不能创建对象，所以只能是接口类引用指向子类对象
        多态：面向抽象编程，不要面向具体编程，降低程序的耦合度，提高程序的扩展力

  类型与类型之间的关系：
        is a 、has a 、like a

        is a：
             Cat is a Animal(猫是一只动物)
             凡是能够满足is a的表示“继承关系”
             A extends B

        has a：
             I has a Pen(我有一只笔)
             凡是能够满足has a的表示“关联关系”
             关联关系通常以“属性”的形式存在
             A {
                B b;
             }

        like a:
             Cooker like a FoodMenu(厨师像一个菜单一样)
             凡是能够满足like a的表示“实现关系”
             实现关系通常是：类实现接口。
              A implements B
 */
public class 抽象类4_接口在开发中作用 {
    public static void main(String[] args) {
        System.out.println("单继承用抽象类，多实现用接口");
    }
}
