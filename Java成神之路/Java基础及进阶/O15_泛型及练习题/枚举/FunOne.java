package O15_泛型及练习题.枚举;

public class FunOne {
    public static void main(String[] args) {
        System.out.println(AccountType.FIXED);
    }
}

enum AccountType {
    SAVING,
    FIXED,
    CURRENT;

    private AccountType() {
        System.out.println("It is a account type");
    }
    // 枚举的构造方法是私有的，不能被外部调用
    // 但是可以在枚举类内部调用
    // 枚举的构造方法在枚举类加载时被调用一次
}
