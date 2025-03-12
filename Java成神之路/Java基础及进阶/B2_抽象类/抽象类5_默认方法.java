package B2_抽象类;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-18 12:24
 * @apiNote TODO 在Java 8之前，一旦一个接口被定义并广泛使用，就不能再向其中添加新的方法，
 *               否则所有实现该接口的类都必须实现新方法，这可能会破坏现有的代码。
 *               Java 8之后有了默认方法，我们可以向接口添加新方法，而不需要修改所有实现该接口的类。
 */
public interface 抽象类5_默认方法 {
    // 抽象方法，子类必须实现
    public abstract void abstractMethod();

    // 带有default关键字的表示为默认方法，子类可以选择使用或覆盖
    public default void defaultMethod() {
        System.out.println("This is a default implementation in the abstract class.");
    }
}

class MyClass implements 抽象类5_默认方法 {

    @Override
    public void abstractMethod() {
        System.out.println("实现接口方法");
    }

    // 默认方法可以重写，也可以不重写。
    // @Override
    // public void defaultMethod() {
    //     抽象类5_默认方法.super.defaultMethod();
    // }
}
