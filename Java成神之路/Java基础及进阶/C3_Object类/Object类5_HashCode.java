package C3_Object类;

import java.util.Objects;

/*
 * hashCode方法：
 *        在Object中的hashCode()方法是怎样的？
 *            public native int hashCode();
 *
 *            这个方法不是抽象方法，带有native关键字，底层调用C++程序。
 *
 *        hashCode()方法返回的是哈希码：
 *            实际上就是一个Java对象的内存地址，经过哈希算法，得出的一个值
 *            所以hashCode()方法的执行结果可以等同看作一个Java对象的内存地址
 */
class Myclass {
    private Integer id;
    @Override
    public String toString() {
        return "Myclass{" +
                "id=" + id +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Myclass myclass = (Myclass) o;
        return Objects.equals(id, myclass.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

public class Object类5_HashCode {
    public static void main(String[] args) {
        Object obj = new Object();
        int hashCodeValue = obj.hashCode();

        // 对象内存地址经过哈希算法转换的一串数字。可以等同看作内存地址。
        System.out.println(hashCodeValue);   // 1442002549

        Myclass mc = new Myclass();
        System.out.println(mc.hashCode());   // 1701381926

        System.out.println(new Myclass().hashCode());
    }
    // 现在我们不需要去弄懂哈希表，哈希码，哈希算法，后面会讲到。
    // 我们现在只需要知道hashCode是将对象的内存地址通过哈希算法转换的数字
}
