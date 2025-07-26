package O15_泛型及练习题.枚举;

public class FunOne {
    public static void main(String[] args) {
        System.out.println(AccountType.FIXED);
        System.out.println(AccountType.FIXED.name());
    }
}

enum AccountType {
    SAVING,
    FIXED,
    CURRENT;

    private AccountType() {
        System.out.println("It is a account type");
    }

    // 枚举的初始化机制：
    //     1、Java 的枚举（enum）本质上是特殊的类，每个枚举常量（如 SAVING, FIXED, CURRENT）都是该类的静态实例。
    //        所以，每一个枚举类都可以通过【类名.枚举名】的方式进行访问。
    //     2、当枚举类被首次访问时（例如调用 AccountType.FIXED），JVM会初始化所有枚举常量，这个过程会触发枚举的构造方法。
    //        且每个枚举常量在初始化时都会调用一次构造方法。（枚举的构造方法是私有的，不能被外部调用，但是可以在枚举类内部调用）
    //        当前枚举有 3 个常量（SAVING, FIXED, CURRENT），因此构造方法会被调用 3 次，打印 3 次 "It is a account type"。
}
