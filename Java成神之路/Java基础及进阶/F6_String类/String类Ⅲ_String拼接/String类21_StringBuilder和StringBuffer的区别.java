package F6_String类.String类Ⅲ_String拼接;

/*
  java.lang.StringBuilder
  StringBuilder和StringBuffer的区别:
      StringBuffer中的方法都有：synchronized关键字修饰，表示StringBuffer在多线程环境中运行是安全的
      StringBuilder中的方法都没有：synchronized关键字修饰，表示StringBuilder在多线程环境中运行是不安全的

      StringBuffer是线程安全的。
      StringBuilder是非线程安全的。
 */
public class String类21_StringBuilder和StringBuffer的区别 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        sb.append("b");
        sb.append("c");
        sb.append("Hello");
        sb.append("World");
        sb.append(100);
        sb.append(true);
        System.out.println(sb.reverse());
    }
}
