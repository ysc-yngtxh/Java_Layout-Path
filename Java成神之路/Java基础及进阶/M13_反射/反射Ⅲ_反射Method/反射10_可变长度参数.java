package M13_反射.反射Ⅲ_反射Method;

/*
  可变长度参数
     int...args  这就是可变长度参数
     语法是：类型...args（注意：一定是3个点)

     1、可变长度参数要求的参数个数是：0~N个
     2、可变长度参数在参数列表中必须在最后一个位置上，而且可变长度参数只能有1个
     3、可变长度参数可以当作一个数组来看待
 */
public class 反射10_可变长度参数 {
    public static void main(String[] args) {
        m();
        m(10);
        m(20);

        m2(100);
        m2(200, "abc");
        m2(300, "abc", "def", "xyz");

        m3("ab", "cd", "ef", "gh");
    }

    public static void m(int... args){
        System.out.println("m方法执行了！");
    }

    public static void m2(int a, String... args1) {    // 可变长度参数一定要写在形参后面，并且一个方法里只能写一个

    }

    public static void m3(String... args){
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
}